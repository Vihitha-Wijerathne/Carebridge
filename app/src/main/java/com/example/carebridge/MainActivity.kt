package com.example.carebridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgHome: ImageButton = findViewById(R.id.navHome)
        val imgUser: ImageButton = findViewById(R.id.navUser)
        val imgFood: ImageButton = findViewById(R.id.navFood)
        val imgEdu: ImageButton = findViewById(R.id.navEdu)
        val imgMedi: ImageButton = findViewById(R.id.navMedi)

        val fragmentHome = Homefragment()
        val fragmentUser = Userfragment()
        val fragmentFood = Foodfragment()
        val fragmentEdu = Educationfragment()
        val fragmentMedi = Medicinefragment()

        imgHome.setOnClickListener {
            imgHome.setImageResource(R.drawable.navbar_selecthome)
            imgUser.setImageResource(R.drawable.navbar_user)
            imgFood.setImageResource(R.drawable.navbar_food)
            imgEdu.setImageResource(R.drawable.navbar_edu)
            imgMedi.setImageResource(R.drawable.navbar_medi)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2, fragmentHome)
                commit()
            }
        }

        imgEdu.setOnClickListener {
            imgHome.setImageResource(R.drawable.navbar_home)
            imgUser.setImageResource(R.drawable.navbar_user)
            imgFood.setImageResource(R.drawable.navbar_food)
            imgEdu.setImageResource(R.drawable.navbar_selectbook)
            imgMedi.setImageResource(R.drawable.navbar_medi)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2, fragmentEdu)
                commit()
            }
        }

        imgFood.setOnClickListener {
            imgHome.setImageResource(R.drawable.navbar_home)
            imgUser.setImageResource(R.drawable.navbar_user)
            imgFood.setImageResource(R.drawable.navbar_selectfood)
            imgEdu.setImageResource(R.drawable.navbar_edu)
            imgMedi.setImageResource(R.drawable.navbar_medi)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2, fragmentFood)
                commit()
            }
        }

        imgMedi.setOnClickListener {
            imgHome.setImageResource(R.drawable.navbar_home)
            imgUser.setImageResource(R.drawable.navbar_user)
            imgFood.setImageResource(R.drawable.navbar_food)
            imgEdu.setImageResource(R.drawable.navbar_edu)
            imgMedi.setImageResource(R.drawable.navbar_selectmedicine)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2, fragmentMedi)
                commit()
            }
        }

        imgUser.setOnClickListener {
            imgHome.setImageResource(R.drawable.navbar_home)
            imgUser.setImageResource(R.drawable.navbar_selectuser)
            imgFood.setImageResource(R.drawable.navbar_food)
            imgEdu.setImageResource(R.drawable.navbar_edu)
            imgMedi.setImageResource(R.drawable.navbar_medi)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2, fragmentUser)
                commit()
            }
        }

    }

}