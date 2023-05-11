package com.example.carebridge

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.carebridge.UserMainFrag.SelectEditType
import com.example.carebridge.UserMainFrag.SelectProjectType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

data class UserModel(
    var email: String? = null,
    var location: String? = null,
    var phoneNumb: String? = null,
    var username: String? = null,
    var website: String? = null,
)
class Userfragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var usernametxt: TextView
    private lateinit var email: TextView
    private lateinit var contactNumb: TextView
    private lateinit var city: TextView
    private lateinit var status: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_userfragment, container, false)
        var userId = ""
        usernametxt = view.findViewById(R.id.usernametxt)
        email = view.findViewById(R.id.useremail)
        contactNumb = view.findViewById(R.id.usertele)
        city = view.findViewById(R.id.usercity)
        status = view.findViewById(R.id.userstatus)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        user?.let {
            userId = it.uid

            database = FirebaseDatabase.getInstance().getReference("users").child(userId)
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val userData = snapshot.getValue(UserModel::class.java)
                        email.text = userData?.email
                        usernametxt.text = userData?.username
                        contactNumb.text = userData?.phoneNumb
                        city.text = userData?.location
                        status.text = userData?.website
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

        val addProjectBtn = view.findViewById<Button>(R.id.addprojectbtn)
        addProjectBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, SelectProjectType())
                .addToBackStack(null)
                .commit()
        }

        val viewProjectBtn = view.findViewById<Button>(R.id.viewprojectsbtn)
        viewProjectBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, SelectEditType())
                .addToBackStack(null)
                .commit()
        }

        val logoutBtn = view.findViewById<Button>(R.id.logout_btn)
        logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }

    private fun updateText(){

    }
}
