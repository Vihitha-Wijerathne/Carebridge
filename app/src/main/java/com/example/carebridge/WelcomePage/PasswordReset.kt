package com.example.carebridge.WelcomePage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carebridge.R
import com.example.carebridge.databinding.ActivityPasswordResetBinding
import com.google.firebase.auth.FirebaseAuth

class PasswordReset : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityPasswordResetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        binding = ActivityPasswordResetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.noAcc.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.resetPwdButton.setOnClickListener{
            val email = binding.resetEmail.text.toString()

            if (email.isNotEmpty()) {
                firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password reset email sent", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Email sent Unsuccessful!", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Incorrect Email!", Toast.LENGTH_SHORT).show()
            }
        }

    }


}