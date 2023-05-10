package com.example.carebridge.Edit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.carebridge.MainActivity
import com.example.carebridge.Models.ProjectModel
import com.example.carebridge.R
import com.google.firebase.database.FirebaseDatabase

class EditFoodCard : AppCompatActivity() {

    private lateinit var prName: TextView
    private lateinit var prDate: TextView
    private lateinit var prTime: TextView
    private lateinit var prLocation: TextView
    private lateinit var prDescription: TextView
    private lateinit var prContact: TextView
    private lateinit var editbutton: Button
    private lateinit var deletebutton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_food_card)


        prName = findViewById(R.id.prName)
        prDate = findViewById(R.id.prDate)
        prTime = findViewById(R.id.prTime)
        prLocation = findViewById(R.id.prLocation)
        prDescription = findViewById(R.id.prDescription)
        prContact = findViewById(R.id.prContact)
        editbutton = findViewById(R.id.edit_button)
        deletebutton = findViewById(R.id.delete_button)

        initView()
        setValuesToViews()
        editbutton.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("pId").toString(),
                intent.getStringExtra("pName").toString()
            )
        }

        deletebutton.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("pId").toString()
            )
        }


    }

    private fun deleteRecord(
        id:String
    ){
        val dbref = FirebaseDatabase.getInstance().getReference("ProjectFood").child(id)
        val mTask = dbref.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Successfully deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            finish()

        }.addOnFailureListener{error ->
            Toast.makeText(this,"Deleting err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {}

    private fun setValuesToViews(){
        prName.text = intent.getStringExtra("pName")
        prDate.text = intent.getStringExtra("pDate")
        prTime.text = intent.getStringExtra("pTime")
        prLocation.text = intent.getStringExtra("pLocation")
        prDescription.text = intent.getStringExtra("pDescription")
        prContact.text = intent.getStringExtra("pContact")

    }

    private fun openUpdateDialog(
        pId: String,
        pname: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val ename = mDialogView.findViewById<EditText>(R.id.ename)
        val edate = mDialogView.findViewById<EditText>(R.id.edate)
        val etime = mDialogView.findViewById<EditText>(R.id.etime)
        val elocation = mDialogView.findViewById<EditText>(R.id.elocation)
        val edescription = mDialogView.findViewById<EditText>(R.id.edescription)
        val econtact = mDialogView.findViewById<EditText>(R.id.econtact)
        val ebtn = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        ename.setText(intent.getStringExtra("pName").toString())
        edate.setText(intent.getStringExtra("pDate").toString())
        etime.setText(intent.getStringExtra("pTime").toString())
        elocation.setText(intent.getStringExtra("pLocation").toString())
        edescription.setText(intent.getStringExtra("pDescription").toString())
        econtact.setText(intent.getStringExtra("pContact").toString())

        mDialog.setTitle("Updating $pname ")

        val alertDialog = mDialog.create()
        alertDialog.show()

        ebtn.setOnClickListener{
            updateProjectData(
                pId,
                ename.text.toString(),
                edate.text.toString(),
                etime.text.toString(),
                elocation.text.toString(),
                edescription.text.toString(),
                econtact.text.toString()
            )
            Toast.makeText(applicationContext,"Updated", Toast.LENGTH_LONG).show()

            prName.text = ename.text.toString()
            prDate.text = edate.text.toString()
            prTime.text = etime.text.toString()
            prLocation.text = elocation.text.toString()
            prDescription.text = edescription.text.toString()
            prContact.text = econtact.text.toString()


            alertDialog.dismiss()
        }

    }

    private fun updateProjectData(
        id:String,
        name:String,
        date:String,
        time:String,
        location:String,
        description:String,
        contact:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("ProjectFood").child(id)

        val proInfo = ProjectModel(id,name,date,time,location,description,contact)

        dbRef.setValue(proInfo)
    }
}