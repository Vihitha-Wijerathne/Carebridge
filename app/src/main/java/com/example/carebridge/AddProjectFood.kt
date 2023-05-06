package com.example.carebridge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.carebridge.Models.ProjectModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddProjectFood : Fragment() {

    private lateinit var dbRef: DatabaseReference

    private lateinit var projectName: EditText
    private lateinit var projectDate: EditText
    private lateinit var projectTime: EditText
    private lateinit var projectLocation: EditText
    private lateinit var projectDescription: EditText
    private lateinit var projectContact: EditText
    private lateinit var projectType: Spinner
    private lateinit var submitBtn: Button


    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_project_food, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinner3)

        val types = arrayOf("Food")
        val arrayAdp = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,types)
        spinner.adapter = arrayAdp

        spinner?.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(requireContext(),"Item is ${types[p2]}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(requireContext(),"Nothing is selected", Toast.LENGTH_LONG).show()
            }
        }

        projectName = view.findViewById(R.id.fp_name)
        projectDate = view.findViewById(R.id.fp_date)
        projectTime = view.findViewById(R.id.fp_time)
        projectLocation = view.findViewById(R.id.fp_place)
        projectDescription = view.findViewById(R.id.freg_description)
        projectContact = view.findViewById(R.id.fp_contact)
        projectType = view.findViewById<Spinner>(R.id.spinner3)
        submitBtn = view.findViewById(R.id.fsubmit_button)

        dbRef = FirebaseDatabase.getInstance().getReference("ProjectFood")

        submitBtn.setOnClickListener {
            saveProjectData()
        }


        return view
    }


    private fun saveProjectData(){
        //getting values
        val pName = projectName.text.toString()
        val pDate = projectDate.text.toString()
        val pTime = projectTime.text.toString()
        val pLocation = projectLocation.text.toString()
        val pDescription = projectDescription.text.toString()
        val pContact = projectContact.text.toString()
        val pType = projectType.selectedItem.toString()

        if (pName.isEmpty()){
            projectName.error = "please enter project name"
        }

        if (pDate.isEmpty()){
            projectDate.error = "please enter project date"
        }

        if (pTime.isEmpty()){
            projectTime.error = "please enter project time"
        }

        if (pLocation.isEmpty()){
            projectLocation.error = "please enter project Location"
        }

        if (pDescription.isEmpty()){
            projectDescription.error = "please enter project Description"
        }

        if (pContact.isEmpty()){
            projectContact.error = "please enter project Contact"
        }

        val projectId = dbRef.push().key!!

        val project = ProjectModel(projectId,pName,pDate,pTime,pLocation,pDescription,pContact,pType)

        dbRef.child(projectId).setValue(project)
            .addOnCompleteListener {
                Toast.makeText(requireContext(),"Data insterted Successfully", Toast.LENGTH_LONG).show()

                projectName.text.clear()
                projectDate.text.clear()
                projectTime.text.clear()
                projectLocation.text.clear()
                projectDescription.text.clear()
                projectContact.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(requireContext(),"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }


}