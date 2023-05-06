package com.example.carebridge

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class Userfragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_userfragment, container, false)

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

        firebaseAuth = FirebaseAuth.getInstance()

        val logoutBtn = view.findViewById<Button>(R.id.logout_btn)
        logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }
}
