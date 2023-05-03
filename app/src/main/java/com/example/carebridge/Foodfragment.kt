package com.example.carebridge

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carebridge.Adaptors.EduAdaptor
import com.example.carebridge.Models.ProjectModel
import com.google.firebase.database.*

class Foodfragment : Fragment() {

    private lateinit var projectsRecyclerView: RecyclerView
    private lateinit var loadingData: TextView
    private lateinit var projectList: ArrayList<ProjectModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_foodfragment, container, false)

        projectsRecyclerView = view.findViewById(R.id.foodprojects)
        projectsRecyclerView.layoutManager = LinearLayoutManager(context)
        projectsRecyclerView.setHasFixedSize(true)
        loadingData = view.findViewById(R.id.foodLoading)

        projectList = arrayListOf<ProjectModel>()

        getProjectData()

        return view
    }

    private fun getProjectData(){
        projectsRecyclerView.visibility = View.GONE
        loadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Project")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                projectList.clear()
                if (snapshot.exists()){
                    for (projectSnap in snapshot.children){
                        val projectData = projectSnap.getValue(ProjectModel::class.java)
                        projectList.add(projectData!!)
                    }
                    val projectAdaptor = EduAdaptor(projectList)
                    projectsRecyclerView.adapter = projectAdaptor

                    projectAdaptor.setOnItemClickListner(object : EduAdaptor.onItemClickListner {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(context, FoodCard::class.java)

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

