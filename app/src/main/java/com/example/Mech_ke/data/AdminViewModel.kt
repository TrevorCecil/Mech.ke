package com.example.Mech_ke.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.Mech_ke.models.Admin
import com.example.Mech_ke.navigation.ADMINLOGIN_URL
import com.example.Mech_ke.navigation.DASH_URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AdminViewModel(var navController: NavHostController, var context: Context) {

    val mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }
    fun signup(name:String, email:String, password:String,){
        progress.show()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            var adminId = mAuth.currentUser!!.uid
            var adminProfile = Admin(name, email, password, adminId)
            // Create a reference table called Admin inside of the Firebase database
            var adminRef = FirebaseDatabase.getInstance().getReference()
                .child("Admin/$adminId")
            adminRef.setValue(adminProfile).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                    navController.navigate(ADMINLOGIN_URL)
                }else{
                    Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun login(email: String, password: String){
        progress.show()
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                navController.navigate(DASH_URL)
            }else{
                Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun logout(){
        mAuth.signOut()
        navController.navigate(ADMINLOGIN_URL)
    }

    fun isLoggedIn(): Boolean = mAuth.currentUser != null

}