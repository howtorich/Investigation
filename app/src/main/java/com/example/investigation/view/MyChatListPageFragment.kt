package com.example.investigation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.investigation.R
import com.example.investigation.adapters.MyChatListAdapter
import com.example.investigation.model.RegResp
import com.example.investigation.model.Register
import com.example.investigation.utils.SharedPreferenceHelper
import com.example.investigation.viewmodel.MyChatListViewModel
import kotlinx.android.synthetic.main.fragment_my_chat_list_page.*


class MyChatListPageFragment() : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
    private lateinit var viewmodel:MyChatListViewModel
    private val listAdapter=MyChatListAdapter(arrayListOf())


    private val userListDataObserver = Observer<List<RegResp>>{list:List<RegResp>->
        list?.let {
            userList.visibility=View.VISIBLE
            listAdapter.updateMyChatList(it)
        }
    }

    private val loadingLivedataObserver= Observer<Boolean> {
        loader.visibility=if(it) View.VISIBLE else View.GONE
        if(it){
            loadError.visibility=View.GONE
            userList.visibility=View.GONE
        }
    }

    private val errorLivedataobserver= Observer<Boolean> {
        loadError.visibility=if(it) View.VISIBLE else View.GONE
        if(it){
            userList.visibility=View.GONE
            loader.visibility=View.GONE
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_chat_list_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val prefs= context?.let { SharedPreferenceHelper(it) }
        goto_globalchat.setOnClickListener {
            val action=MyChatListPageFragmentDirections.actionGlobalchat()
            Navigation.findNavController(it).navigate(action)
        }

        viewmodel = ViewModelProviders.of(this).get(MyChatListViewModel::class.java)
        val actionId:Int=102
        val uname= prefs?.getUName()
        val uid=prefs?.getUID()
       viewmodel.postRegisterDetails(Register(actionId, RegResp(uname,uid?.toLong())))
        //viewmodel.postRegisterDetails(Register(actionId, RegResp(null,1)))

        viewmodel.users.observe(this,userListDataObserver)
        viewmodel.loading.observe(this,loadingLivedataObserver)
        viewmodel.loadError.observe(this,errorLivedataobserver)

        //viewmodel.refresh()

        userList.apply {
            layoutManager=GridLayoutManager(context,3)
            adapter=listAdapter
        }
        refreshLayout.setOnRefreshListener {
            loadError.visibility=View.GONE
            loader.visibility=View.VISIBLE
            viewmodel.postRegisterDetails(Register(actionId, RegResp(uname,uid?.toLong())))
            //viewmodel.postRegisterDetails(Register(actionId, RegResp(null,1)))
            refreshLayout.isRefreshing=false
        }


    }

}