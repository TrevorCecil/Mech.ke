package com.example.wazitoecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.wazitoecommerce.models.Autoshop
import com.example.wazitoecommerce.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class TestViewModel (var navController:NavHostController, var context: Context){
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
        val autoId = System.currentTimeMillis().toString()
        val storage_auto_Ref = FirebaseStorage.getInstance().getReference()
            .child("Autos/$autoId")
        progress.show()
        storage_auto_Ref.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storage_auto_Ref.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var auto = Autoshop(name,contact,county,town,imageUrl,autoId)
                    var database_auto_Ref = FirebaseDatabase.getInstance().getReference()
                        .child("Autos/$autoId")
                    database_auto_Ref.setValue(auto).addOnCompleteListener {
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
        auto:MutableState<Autoshop>,
        autos:SnapshotStateList<Autoshop>):SnapshotStateList<Autoshop>{
        progress.show()
        var autoref = FirebaseDatabase.getInstance().getReference()
            .child("Autos")
        autoref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                autos.clear()
                for (snap in snapshot.children){
                    var retrievedAuto = snap.getValue(Autoshop::class.java)
                    auto.value = retrievedAuto!!
                    autos.add(retrievedAuto)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return autos
    }

    fun deleteProduct(autoId:String){
        var autoref = FirebaseDatabase.getInstance().getReference()
            .child("Autos/$autoId")
        autoref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}

