package com.example.carebridge.Add

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.carebridge.MainActivity
import com.example.carebridge.Models.ProjectModel
import com.example.carebridge.R
import com.example.carebridge.UserMainFrag.SelectEditType
import com.example.carebridge.UserMainFrag.SelectProjectType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddProject : Fragment() {

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
    private lateinit var vCount: Number


    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_project, container, false)

        val back_button = view.findViewById<ImageView>(R.id.back_button)
        back_button.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        val spinner = view.findViewById<Spinner>(R.id.spinner1)

        val types = arrayOf("Education")
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

        projectName = view.findViewById(R.id.p_name)
        projectDate = view.findViewById(R.id.p_date)
        projectTime = view.findViewById(R.id.p_time)
        projectLocation = view.findViewById(R.id.p_place)
        projectDescription = view.findViewById(R.id.reg_description)
        projectContact = view.findViewById(R.id.p_contact)
        projectType = view.findViewById<Spinner>(R.id.spinner1)
        submitBtn = view.findViewById(R.id.submit_button)
        vCount = 0

        dbRef = FirebaseDatabase.getInstance().getReference("Project")

        submitBtn.setOnClickListener {
            saveProjectData()
        }

        val backBtn = view.findViewById<ImageView>(R.id.back_button)
        backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, SelectProjectType())
                .addToBackStack(null)
                .commit()
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
            vCount = vCount as Int + 1
        }

        if (pDate.isEmpty()){
            projectDate.error = "please enter project date"
            vCount = vCount as Int + 1
        }

        if (pTime.isEmpty()){
            projectTime.error = "please enter project time"
            vCount = vCount as Int + 1
        }

        if (pLocation.isEmpty()){
            projectLocation.error = "please enter project Location"
            vCount = vCount as Int + 1
        }

        if (pDescription.isEmpty()){
            projectDescription.error = "please enter project Description"
            vCount = vCount as Int + 1
        }

        if (pContact.isEmpty()){
            projectContact.error = "please enter project Contact"
            vCount = vCount as Int + 1
        }

        if(vCount == 0) {
            firebaseAuth = FirebaseAuth.getInstance()
            val user = firebaseAuth.currentUser
            var projectId = ""

            user?.let {
                val uid = it.uid

                projectId = dbRef.push().key!!
                project = ProjectModel(
                    uid,
                    projectId,
                    pName,
                    pDate,
                    pTime,
                    pLocation,
                    pDescription,
                    pContact,
                    pType
                )
            }

            dbRef.child(projectId).setValue(project)
                .addOnCompleteListener {
                    Toast.makeText(
                        requireContext(),
                        "Data insterted Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    projectName.text.clear()
                    projectDate.text.clear()
                    projectTime.text.clear()
                    projectLocation.text.clear()
                    projectDescription.text.clear()
                    projectContact.text.clear()

                }.addOnFailureListener { err ->
                    Toast.makeText(requireContext(), "Error ${err.message}", Toast.LENGTH_LONG)
                        .show()
                }
        }else {
            vCount = 0
        }
    }
}