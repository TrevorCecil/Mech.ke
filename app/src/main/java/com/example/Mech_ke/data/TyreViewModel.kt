package com.example.Mech_ke.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.Mech_ke.models.Tyre
import com.example.Mech_ke.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class TyreViewModel (var navController: NavHostController, var context: Context){
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
        val tyreId = System.currentTimeMillis().toString()
        val storage_tyre_Ref = FirebaseStorage.getInstance().getReference()
            .child("Tyres/$tyreId")
        progress.show()
        storage_tyre_Ref.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storage_tyre_Ref.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var tyre = Tyre(name,contact,county,town,imageUrl,tyreId)
                    var database_tyre_Ref = FirebaseDatabase.getInstance().getReference()
                        .child("Tyres/$tyreId")
                    database_tyre_Ref.setValue(tyre).addOnCompleteListener {
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
        tyre: MutableState<Tyre>,
        tyres: SnapshotStateList<Tyre>
    ): SnapshotStateList<Tyre> {
        progress.show()
        var tyre_ref = FirebaseDatabase.getInstance().getReference()
            .child("Tyres")
        tyre_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tyres.clear()
                for (snap in snapshot.children){
                    var retrievedtyre = snap.getValue(Tyre::class.java)
                    tyre.value = retrievedtyre!!
                    tyres.add(retrievedtyre)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return tyres
    }

    fun deleteProduct(tyreId:String){
        var tyre_ref = FirebaseDatabase.getInstance().getReference()
            .child("Tyres/$tyreId")
        tyre_ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}
