package com.example.investigation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.investigation.R
import com.example.investigation.databinding.ActivityLaunchActiivtyBinding
import com.example.investigation.model.RegResp
import com.example.investigation.model.Register

import com.example.investigation.utils.SharedPreferenceHelper
import com.example.investigation.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_launch_actiivty.*
import kotlinx.android.synthetic.main.fragment_my_chat_list_page.*

class LaunchActiivty : AppCompatActivity(),UserClickListener {
    private lateinit var viewModel: RegisterViewModel

    private val submitButtonObserver= Observer<Boolean> {
        if(it){
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLaunchActiivtyBinding=DataBindingUtil.setContentView(this,R.layout.activity_launch_actiivty)
        binding.launch=this
        val prefs= SharedPreferenceHelper(getApplication())
        if(!prefs.getUName().isNullOrEmpty()){
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //val text=findViewById<EditText>(R.id.userEmail)
       // val click_op=findViewById<Button>(R.id.send)

    }

    override fun onClick(v: View) {
        val userEmail:String=userEmail.text.toString()
        val actionId:Int=100

        viewModel=ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        viewModel.subButton.observe(this,submitButtonObserver)
        val registerModel=Register(actionId, RegResp(userEmail,0),null)
        val response=viewModel.postRegisterDetails(registerModel)
    }


}