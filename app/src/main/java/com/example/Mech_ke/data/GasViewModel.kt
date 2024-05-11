package com.example.Mech_ke.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.Mech_ke.models.Gas
import com.example.Mech_ke.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class GasViewModel (var navController: NavHostController, var context: Context){

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

    fun uploadProduct(name:String, county:String,town:String, filePath: Uri){
        val gasId = System.currentTimeMillis().toString()
        val storagegasRef = FirebaseStorage.getInstance().getReference()
            .child("Gas/$gasId")
        progress.show()
        storagegasRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storagegasRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var gas = Gas(name,county,town,imageUrl,gasId)
                    var databasegasRef = FirebaseDatabase.getInstance().getReference()
                        .child("Gas/$gasId")
                    databasegasRef.setValue(gas).addOnCompleteListener {
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
        gas: MutableState<Gas>,
        gas_s: SnapshotStateList<Gas>
    ): SnapshotStateList<Gas> {
        progress.show()
        var gasref = FirebaseDatabase.getInstance().getReference()
            .child("Gas")
        gasref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gas_s.clear()
                for (snap in snapshot.children){
                    var retrievedGas = snap.getValue(Gas::class.java)
                    gas.value = retrievedGas!!
                    gas_s.add(retrievedGas)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return gas_s
    }

    fun deleteProduct(gasId:String){
        var gasref = FirebaseDatabase.getInstance().getReference()
            .child("Gas/$gasId")
        gasref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}

