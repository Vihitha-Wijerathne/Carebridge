package com.example.carebridge.Adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carebridge.Models.ProjectModel
import com.example.carebridge.R


class FoodAdaptor (private val projectList: ArrayList<ProjectModel>) : RecyclerView.Adapter<FoodAdaptor.ViewHolder>() {

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        mListner = clickListner
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_card_view, parent,false)
        return FoodAdaptor.ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = projectList[position]

        holder.projectName.text = currentItem.projectName
        holder.projectDescription.text = currentItem.projectDescription
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner) : RecyclerView.ViewHolder(itemView) {

        val projectName : TextView = itemView.findViewById(R.id.educardtopic)
        val projectDescription : TextView = itemView.findViewById(R.id.educard_description)

        init {
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }
}