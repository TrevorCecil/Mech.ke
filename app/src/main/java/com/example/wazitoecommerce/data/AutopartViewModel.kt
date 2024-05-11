package com.example.wazitoecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.wazitoecommerce.models.Autopart
import com.example.wazitoecommerce.models.Gas
import com.example.wazitoecommerce.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class AutopartViewModel (var navController: NavHostController, var context: Context){
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

    fun uploadProduct(name:String,price:String,condition:String,location:String,description:String, filePath: Uri){
        val autopartId = System.currentTimeMillis().toString()
        val storage_autopart_Ref = FirebaseStorage.getInstance().getReference()
            .child("Auto_parts/$autopartId")
        progress.show()
        storage_autopart_Ref.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storage_autopart_Ref.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var auto_part = Autopart(name,price,condition,location,description,imageUrl,autopartId)
                    var database_autopart_Ref = FirebaseDatabase.getInstance().getReference()
                        .child("Auto_parts/$autopartId")
                    database_autopart_Ref.setValue(auto_part).addOnCompleteListener {
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
        auto_part: MutableState<Autopart>,
        auto_parts: SnapshotStateList<Autopart>
    ): SnapshotStateList<Autopart> {
        progress.show()
        var auto_part_ref = FirebaseDatabase.getInstance().getReference()
            .child("Auto_parts")
        auto_part_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                auto_parts.clear()
                for (snap in snapshot.children){
                    var retrievedAuto_part = snap.getValue(Autopart::class.java)
                    auto_part.value = retrievedAuto_part!!
                    auto_parts.add(retrievedAuto_part)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return auto_parts
    }

    fun deleteProduct(autopartId:String){
        var auto_part_ref = FirebaseDatabase.getInstance().getReference()
            .child("Auto_parts/$autopartId")
        auto_part_ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}


