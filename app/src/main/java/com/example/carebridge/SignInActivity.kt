package com.example.carebridge

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.carebridge.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.noAcc.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.forgetPwd.setOnClickListener{
            val intent = Intent(this, PasswordReset::class.java)
            startActivity(intent)
        }

        binding.logButton.setOnClickListener{
            val email = binding.logEmail.text.toString()
            val pass = binding.logPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                            val user = firebaseAuth.currentUser
                            user?.let {
                                // Name, email address, and profile photo Url
                                val name = it.displayName
                                val email = it.email
                                val photoUrl = it.photoUrl

                                // Check if user's email is verified
                                val emailVerified = it.isEmailVerified

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getIdToken() instead.
                                val uid = it.uid

                                println(name)
                                println(email)
                                println(photoUrl)
                                println(emailVerified)
                                println(uid)
                            }

                        } else {
                            Toast.makeText(this, "Email or Password is Incorrect", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this,"Empty Fields Are Not Allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}