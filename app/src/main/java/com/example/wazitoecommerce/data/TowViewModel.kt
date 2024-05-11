package com.example.wazitoecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.wazitoecommerce.models.Autoshop
import com.example.wazitoecommerce.models.Tow
import com.example.wazitoecommerce.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class TowViewModel (var navController: NavHostController, var context: Context){
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
        val towId = System.currentTimeMillis().toString()
        val storage_Tow_Ref = FirebaseStorage.getInstance().getReference()
            .child("Tows/$towId")
        progress.show()
        storage_Tow_Ref.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storage_Tow_Ref.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var tow = Tow(name,contact,county,town,imageUrl,towId)
                    var database_Tow_Ref = FirebaseDatabase.getInstance().getReference()
                        .child("Tows/$towId")
                    database_Tow_Ref.setValue(tow).addOnCompleteListener {
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
        tow: MutableState<Tow>,
        tows: SnapshotStateList<Tow>
    ): SnapshotStateList<Tow> {
        progress.show()
        var tow_ref = FirebaseDatabase.getInstance().getReference()
            .child("Tows")
        tow_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tows.clear()
                for (snap in snapshot.children){
                    var retrievedTow = snap.getValue(Tow::class.java)
                    tow.value = retrievedTow!!
                    tows.add(retrievedTow)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return tows
    }

    fun deleteProduct(towId:String){
        var tow_ref = FirebaseDatabase.getInstance().getReference()
            .child("Tows/$towId")
        tow_ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}


