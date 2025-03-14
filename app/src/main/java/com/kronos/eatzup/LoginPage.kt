package com.kronos.eatzup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.kronos.eatzup.databinding.ActivityLoginPageBinding
import com.kronos.model.Mvvm

class LoginPage : AppCompatActivity() {

    private val loginBinding: ActivityLoginPageBinding by lazy {
        ActivityLoginPageBinding.inflate(layoutInflater)
    }

    private val userName: String? = null
    private val restaurantName: String? = null
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(loginBinding.root)

        //initialize
        auth = com.google.firebase.Firebase.auth
        database = Firebase.database.reference

        configureGoogleSignIn()
        configureSignInLauncher()

        loginBinding.createAccount.setOnClickListener {
            startActivity(Intent(this, SignUpPage::class.java))
        }

        loginBinding.loginButton.setOnClickListener {
            email = loginBinding.emailAddress.text.toString().trim()
            password = loginBinding.emailAddress.text.toString().trim()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "oops some blanks are empty", Toast.LENGTH_SHORT).show()
            } else {
                loginWithAccount(email, password)
            }
        }

        loginBinding.signInButton.setOnClickListener {
            signInWithGoogle()
        }

        signInWithGoogle()


    }

    private fun loginWithAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                saveData()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Log.e("error", "Error ${task.exception?.message}")
            }
        }
    }

    private fun saveData() {
        email = loginBinding.emailAddress.text.toString().trim()
        password = loginBinding.password.text.toString().trim()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val user = Mvvm(userName, restaurantName, email, password)

        userId?.let {
            database.child("user").child(it).setValue(user)
        }

    }

    private fun configureGoogleSignIn() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun configureSignInLauncher() {

        signInLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account)
                } catch (e: ApiException) {
                    Log.e("GoogleSignIN", "Google sign-in failed")
                }

            }

    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credentials = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credentials).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Log.e("GoogleSignIN", "Firebase authenticate failed", task.exception)

            }
        }
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }


}