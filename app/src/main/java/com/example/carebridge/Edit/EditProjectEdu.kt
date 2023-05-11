package com.example.carebridge.Edit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carebridge.Adaptors.EduAdaptor
import com.example.carebridge.Models.ProjectModel
import com.example.carebridge.R
import com.example.carebridge.UserMainFrag.SelectEditType
import com.example.carebridge.UserMainFrag.SelectProjectType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class EditProjectEdu : Fragment() {

    private lateinit var projectsRecyclerView: RecyclerView
    private lateinit var loadingData: TextView
    private lateinit var projectList: ArrayList<ProjectModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var projectCount: TextView
    private lateinit var count: Number

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_project_edu, container, false)

        projectsRecyclerView = view.findViewById(R.id.educationprojects)
        projectsRecyclerView.layoutManager = LinearLayoutManager(context)
        projectsRecyclerView.setHasFixedSize(true)
        loadingData = view.findViewById(R.id.educationLoading)
        projectCount = view.findViewById(R.id.pcount)
        count = 0


        projectList = arrayListOf<ProjectModel>()

        getProjectData()



        val backBtn = view.findViewById<ImageView>(R.id.back_button2)
        backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, SelectEditType())
                .addToBackStack(null)
                .commit()
        }




        return view
    }


    private fun getProjectData(){
        var userId = ""

        projectsRecyclerView.visibility = View.GONE
        loadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Project")

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.let {
            userId = it.uid
        }

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                projectList.clear()
                if (snapshot.exists()){

                    for (projectSnap in snapshot.children){
                        val projectData = projectSnap.getValue(ProjectModel::class.java)

                        if(projectData?.userId.equals(userId)) {
                            count = count as Int + 1
                            projectList.add(projectData!!)
                        }
                    }
                    val projectAdaptor = EduAdaptor(projectList)
                    projectsRecyclerView.adapter = projectAdaptor

                    projectAdaptor.setOnItemClickListner(object : EduAdaptor.onItemClickListner {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(context, EditEduCard::class.java)

                            //put extras
                            intent.putExtra("pId", projectList[position].projectId)
                            intent.putExtra("pName", projectList[position].projectName)
                            intent.putExtra("pDate", projectList[position].projectDate)
                            intent.putExtra("pTime", projectList[position].projectTime)
                            intent.putExtra("pLocation", projectList[position].projectLocation)
                            intent.putExtra("pDescription", projectList[position].projectDescription)
                            intent.putExtra("pContact", projectList[position].projectContact)
                            startActivity(intent)

                        }

                    })
                    projectCount.text = count.toString()

                    projectsRecyclerView.visibility = View.VISIBLE
                    loadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}