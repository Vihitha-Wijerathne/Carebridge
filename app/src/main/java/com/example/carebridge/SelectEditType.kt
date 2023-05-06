package com.example.carebridge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class SelectEditType : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_edit_type, container, false)

        val addEduBtn = view.findViewById<Button>(R.id.edubtn)
        addEduBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, EditProjectEdu())
                .addToBackStack(null)
                .commit()
        }

        val addFoodBtn = view.findViewById<Button>(R.id.foodbtn)
        addFoodBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, EditProjectFood())
                .addToBackStack(null)
                .commit()
        }

        val addMediBtn = view.findViewById<Button>(R.id.medibtn)
        addMediBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, EditProjectMedi())
                .addToBackStack(null)
                .commit()
        }



        return view
    }


}