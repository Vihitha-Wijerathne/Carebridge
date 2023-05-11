package com.example.carebridge.UserMainFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.carebridge.Add.AddProject
import com.example.carebridge.Add.AddProjectFood
import com.example.carebridge.Add.AddProjectMedi
import com.example.carebridge.R


class SelectProjectType : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_project_type, container, false)

        val addEduBtn = view.findViewById<Button>(R.id.edubtn)
        addEduBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, AddProject())
                .addToBackStack(null)
                .commit()
        }

        val addFoodBtn = view.findViewById<Button>(R.id.foodbtn)
        addFoodBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, AddProjectFood())
                .addToBackStack(null)
                .commit()
        }

        val addMediBtn = view.findViewById<Button>(R.id.medibtn)
        addMediBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, AddProjectMedi())
                .addToBackStack(null)
                .commit()
        }

        return view
    }


}