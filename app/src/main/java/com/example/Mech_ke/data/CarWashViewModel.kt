package com.example.Mech_ke.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.Mech_ke.models.CarWash
import com.example.Mech_ke.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class CarWashViewModel (var navController: NavHostController, var context: Context){

    var authViewModel:AdminViewModel
    var progress: ProgressDialog
    init {
        authViewModel = AdminViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(ADMINLOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadProduct(name:String, contact:String, county:String,town:String, filePath: Uri){
        val carId = System.currentTimeMillis().toString()
        val storagecarRef = FirebaseStorage.getInstance().getReference()
            .child("Cars/$carId")
        progress.show()
        storagecarRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storagecarRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var car = CarWash(name,contact,county,town,imageUrl,carId)
                    var databasecarRef = FirebaseDatabase.getInstance().getReference()
                        .child("Cars/$carId")
                    databasecarRef.setValue(car).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun allProducts(
        car: MutableState<CarWash>,
        cars: SnapshotStateList<CarWash>
    ): SnapshotStateList<CarWash> {
        progress.show()
        var carref = FirebaseDatabase.getInstance().getReference()
            .child("Cars")
        carref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cars.clear()
                for (snap in snapshot.children){
                    var retrievedCar = snap.getValue(CarWash::class.java)
                    car.value = retrievedCar!!
                    cars.add(retrievedCar)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return cars
    }

    fun deleteProduct(carId:String){
        var carref = FirebaseDatabase.getInstance().getReference()
            .child("Cars/$carId")
        carref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}
