package com.example.Mech_ke.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.Mech_ke.models.Showroom
import com.example.Mech_ke.navigation.ADMINLOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ShowroomViewModel(var navController: NavHostController, var context: Context) {
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
        val showId = System.currentTimeMillis().toString()
        val storageshowRef = FirebaseStorage.getInstance().getReference()
            .child("Shows/$showId")
        progress.show()
        storageshowRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageshowRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var show = Showroom(name,contact,county,town,imageUrl,showId)
                    var databaseshowRef = FirebaseDatabase.getInstance().getReference()
                        .child("Shows/$showId")
                    databaseshowRef.setValue(show).addOnCompleteListener {
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
        show: MutableState<Showroom>,
        shows: SnapshotStateList<Showroom>
    ): SnapshotStateList<Showroom> {
        progress.show()
        var showref = FirebaseDatabase.getInstance().getReference()
            .child("Shows")
        showref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                shows.clear()
                for (snap in snapshot.children){
                    var retrievedShow = snap.getValue(Showroom::class.java)
                    show.value = retrievedShow!!
                    shows.add(retrievedShow)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return shows
    }

    fun deleteProduct(showId:String){
        var showref = FirebaseDatabase.getInstance().getReference()
            .child("Shows/$showId")
        showref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}
