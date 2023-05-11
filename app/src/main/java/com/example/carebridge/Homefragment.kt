package com.example.carebridge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.carebridge.EducationMainFrag.Educationfragment
import com.example.carebridge.FoodMainFrag.Foodfragment
import com.example.carebridge.MedicineMainFrag.Medicinefragment

class Homefragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_homefragment, container, false)

        val nextText1 = view.findViewById<ImageButton>(R.id.edubutton)
        nextText1.setOnClickListener {
            val nextFragment = Educationfragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView2, nextFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        val nextText2 = view.findViewById<ImageButton>(R.id.foodbutton)
        nextText2.setOnClickListener {
            val nextFragment = Foodfragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView2, nextFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        val nextText3 = view.findViewById<ImageButton>(R.id.medibutton)
        nextText3.setOnClickListener {
            val nextFragment = Medicinefragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView2, nextFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        return view
    }

}