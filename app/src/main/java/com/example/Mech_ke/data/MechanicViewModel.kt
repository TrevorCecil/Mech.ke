package com.example.Mech_ke.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.Mech_ke.models.Mechanic
import com.example.Mech_ke.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class MechanicViewModel (var navController:NavHostController, var context: Context){
    var authViewModel:AdminViewModel
    var progress:ProgressDialog
    init {
        authViewModel = AdminViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(ADMINLOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadProduct(name:String, contact:String, county:String,town:String, filePath:Uri){
        val mechId = System.currentTimeMillis().toString()
        val storagemechRef = FirebaseStorage.getInstance().getReference()
            .child("Mechs/$mechId")
        progress.show()
        storagemechRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storagemechRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var mech = Mechanic(name,contact,county,town,imageUrl,mechId)
                    var databasemechRef = FirebaseDatabase.getInstance().getReference()
                        .child("Mechs/$mechId")
                    databasemechRef.setValue(mech).addOnCompleteListener {
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
        mech: MutableState<Mechanic>,
        mechs: SnapshotStateList<Mechanic>
    ): SnapshotStateList<Mechanic> {
        progress.show()
        var mechref = FirebaseDatabase.getInstance().getReference()
            .child("Mechs")
        mechref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mechs.clear()
                for (snap in snapshot.children){
                    var retrievedMech = snap.getValue(Mechanic::class.java)
                    mech.value = retrievedMech!!
                    mechs.add(retrievedMech)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return mechs
    }

    fun deleteProduct(mechId:String){
        var mechref = FirebaseDatabase.getInstance().getReference()
            .child("Mechs/$mechId")
        mechref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}

