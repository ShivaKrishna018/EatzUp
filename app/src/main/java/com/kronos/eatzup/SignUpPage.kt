package com.kronos.eatzup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.kronos.eatzup.databinding.ActivitySignUpPageBinding
import com.kronos.model.Mvvm

class SignUpPage : AppCompatActivity() {
    private val signBinding: ActivitySignUpPageBinding by lazy {
        ActivitySignUpPageBinding.inflate(layoutInflater)
    }

    lateinit var username: String
    lateinit var restaurantName: String
    lateinit var email: String
    lateinit var password: String

    lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(signBinding.root)

        // initialize
        auth = Firebase.auth
        database = Firebase.database.reference

        signBinding.alreadyAccount.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }

        signBinding.createAccount.setOnClickListener {
            username = signBinding.userName.text.toString().trim()
            email = signBinding.emailAddress.text.toString().trim()
            password = signBinding.password.text.toString().trim()
            restaurantName = signBinding.restaurantName.text.toString().trim()

            if (username.isBlank() || restaurantName.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Oops, looks like a few fields are missing.", Toast.LENGTH_SHORT).show()
            }else {
                authenticate(email, password)
            }
        }
    }

    private fun authenticate(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if(task.isSuccessful) {
                saveData()
                Toast.makeText(this, "account created", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginPage::class.java))
                finish()
            }else {
                Toast.makeText(this, "failed ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveData() {

        username = signBinding.userName.text.toString().trim()
        restaurantName = signBinding.restaurantName.text.toString().trim()
        email = signBinding.emailAddress.text.toString().trim()
        password = signBinding.password.text.toString().trim()

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val userId = currentUser.uid
            val user = Mvvm(username, restaurantName, email, password)
            database.child("user").child(userId).setValue(user).addOnCompleteListener {
                taskInfo ->
                if(taskInfo.isSuccessful) {
                    Log.d("Firebase", "Data saved successfully")
                    Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show()

                }else {
                    Log.e("Firebase", "Error saving data: ${taskInfo.exception?.message}")
                    Toast.makeText(this, "failed: ${taskInfo.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}