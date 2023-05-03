package com.example.carebridge

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Userfragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_userfragment, container, false)

        val addProjectBtn = view.findViewById<Button>(R.id.addprojectbtn)
        addProjectBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, AddProject())
                .addToBackStack(null)
                .commit()
        }

        val viewProjectBtn = view.findViewById<Button>(R.id.viewprojectsbtn)
        viewProjectBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, EditProject())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
