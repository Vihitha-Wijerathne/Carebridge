package com.example.carebridge.Add

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.carebridge.Models.ProjectModel
import com.example.carebridge.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddProjectMedi : Fragment() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var projectName: EditText
    private lateinit var projectDate: EditText
    private lateinit var projectTime: EditText
    private lateinit var projectLocation: EditText
    private lateinit var projectDescription: EditText
    private lateinit var projectContact: EditText
    private lateinit var projectType: Spinner
    private lateinit var submitBtn: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var project: ProjectModel

    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_project_medi, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinner2)

        val types = arrayOf("Medicine")
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

        projectName = view.findViewById(R.id.mp_name)
        projectDate = view.findViewById(R.id.mp_date)
        projectTime = view.findViewById(R.id.mp_time)
        projectLocation = view.findViewById(R.id.mp_place)
        projectDescription = view.findViewById(R.id.mreg_description)
        projectContact = view.findViewById(R.id.mp_contact)
        projectType = view.findViewById<Spinner>(R.id.spinner2)
        submitBtn = view.findViewById(R.id.msubmit_button)

        dbRef = FirebaseDatabase.getInstance().getReference("ProjectMedi")

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

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        var projectId = ""

        user?.let {
            val uid = it.uid

            projectId = dbRef.push().key!!
            project = ProjectModel(uid, projectId,pName,pDate,pTime,pLocation,pDescription,pContact,pType)
        }

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