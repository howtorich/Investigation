package com.example.investigation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.investigation.R
import com.example.investigation.model.RegResp
import com.example.investigation.model.Register

import com.example.investigation.utils.SharedPreferenceHelper
import com.example.investigation.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_launch_actiivty.*

class LaunchActiivty : AppCompatActivity() {
    private lateinit var viewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs= SharedPreferenceHelper(getApplication())
        if(!prefs.getUName().isNullOrEmpty()){
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(R.layout.activity_launch_actiivty)
        val text=findViewById<EditText>(R.id.userEmail)
        val click_op=findViewById<Button>(R.id.send)


        click_op.setOnClickListener {
            val userEmail:String=text.text.toString()
            val actionId:Int=100

            viewModel=ViewModelProviders.of(this).get(RegisterViewModel::class.java)
            val registerModel=Register(actionId, RegResp(userEmail,0))
            val response=viewModel.postRegisterDetails(registerModel)
            if(response){
                intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }





    }


}