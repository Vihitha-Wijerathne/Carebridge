package com.example.carebridge

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.carebridge.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("users")

        binding.alreadyAcc.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.regButton.setOnClickListener{
            val name = binding.pName.text.toString()
            val phoneNumb = binding.regPhone.text.toString()
            val website = binding.website.text.toString()
            val location = binding.location.text.toString()
            val email = binding.regEmail.text.toString()
            val pass = binding.regPassword.text.toString()
            val confirmPass = binding.rePassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass.equals(confirmPass)){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            @IgnoreExtraProperties
                            data class User(val username: String? = null, val email: String? = null, val phoneNumb: String? = null, val website: String? = null, val location: String? = null) {
                                // Null default values create a no-argument default constructor, which is needed
                                // for deserialization from a DataSnapshot.
                            }

                            fun writeNewUser(userId: String, name: String, email: String, phoneNumb: String, website: String, location: String) {
                                val user = User(name, email, phoneNumb, website, location)
                                database.child(userId).setValue(user)
                            }

                            val user = firebaseAuth.currentUser
                            user?.let {
                                val uid = it.uid

                                writeNewUser(uid, name, email, phoneNumb, website, location)
                            }

                            val intent = Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty Fields Are Not Allowed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}