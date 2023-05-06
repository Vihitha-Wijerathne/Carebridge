package com.example.carebridge

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.carebridge.databinding.ActivityFoodCardBinding

class FoodCard : AppCompatActivity() {

    private lateinit var prName: TextView
    private lateinit var prDate: TextView
    private lateinit var prTime: TextView
    private lateinit var prLocation: TextView
    private lateinit var prDescription: TextView
    private lateinit var prContact: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_card)

        prName = findViewById(R.id.prName)
        prDate = findViewById(R.id.prDate)
        prTime = findViewById(R.id.prTime)
        prLocation = findViewById(R.id.prLocation)
        prDescription = findViewById(R.id.prDescription)
        prContact = findViewById(R.id.prContact)

        initView()
        setValuesToViews()
    }

    private fun initView(){}

    private fun setValuesToViews(){
        prName.text = intent.getStringExtra("pName")
        prDate.text = intent.getStringExtra("pDate")
        prTime.text = intent.getStringExtra("pTime")
        prLocation.text = intent.getStringExtra("pLocation")
        prDescription.text = intent.getStringExtra("pDescription")
        prContact.text = intent.getStringExtra("pContact")
    }
}