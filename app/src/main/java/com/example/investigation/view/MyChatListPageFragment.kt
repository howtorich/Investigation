package com.example.investigation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.investigation.R
import com.example.investigation.adapters.MyChatListAdapter
import com.example.investigation.model.*
import com.example.investigation.utils.SharedPreferenceHelper
import com.example.investigation.viewmodel.MyChatListViewModel
import kotlinx.android.synthetic.main.fragment_my_chat_list_page.*


class MyChatListPageFragment() : Fragment() {

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
    private lateinit var viewmodel: MyChatListViewModel
    private val listAdapter =
        MyChatListAdapter(arrayListOf()) { item: RegResp?, view: View -> adapterClick(item, view) }
    private lateinit var userView: View


    private val userListDataObserver = Observer<List<RegResp>> { list: List<RegResp> ->
        list.let {
            userList.visibility = View.VISIBLE
            listAdapter.updateMyChatList(it)
        }
    }

    private val loadingLivedataObserver = Observer<Boolean> {
        loader.visibility = if (it) View.VISIBLE else View.GONE
        if (it) {
            loadError.visibility = View.GONE
            userList.visibility = View.GONE
        }
    }

    private val errorLivedataobserver = Observer<Boolean> {
        loadError.visibility = if (it) View.VISIBLE else View.GONE
        if (it) {
            userList.visibility = View.GONE
            loader.visibility = View.GONE
        }

    }
    private val userDeatailsLiveDataObserver = Observer<RegResponse> {
        it?.let {
            (activity as? AppCompatActivity)?.supportActionBar?.title =
                it.userDetailsModel?.username
        }
    }

    private val myChatPageUserClickDetailsObserver = Observer<RegResponse> {
        val res = it.chatConversationModel
        it?.let {
            if (it.chatConversationModel != null) {
                context.let {
                    Toast.makeText(it, "Success", Toast.LENGTH_SHORT).show()
                }
                context.let {
                    val action =
                        MyChatListPageFragmentDirections.actionMychatlistpagetouserchatpage()
                    Navigation.findNavController(userView).navigate(action)
                }

            }
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
        return inflater.inflate(R.layout.fragment_my_chat_list_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        addFriendNavButton.setOnClickListener {
            val action = MyChatListPageFragmentDirections.actionGlobalchat()
            //listAdapter.temp=false
            Navigation.findNavController(it).navigate(action)
        }


        viewmodel = ViewModelProviders.of(this).get(MyChatListViewModel::class.java)
        val actionId: Int = 102
        val prefs = context?.let { SharedPreferenceHelper(it) }
        val uname = prefs?.getUName()
        val uid = prefs?.getUID()
        viewmodel.postRegisterDetails(Register(actionId, RegResp(uname, uid?.toLong()), null))
        //viewmodel.postRegisterDetails(Register(actionId, RegResp("",1)))

        viewmodel.users.observe(viewLifecycleOwner, userListDataObserver)
        viewmodel.loading.observe(viewLifecycleOwner, loadingLivedataObserver)
        viewmodel.loadError.observe(viewLifecycleOwner, errorLivedataobserver)

        viewmodel.gettingUserDetails(Register(105, RegResp(uname, uid?.toLong()), null))
        viewmodel.userDetails.observe(viewLifecycleOwner, userDeatailsLiveDataObserver)

        //viewmodel.refresh()

        userList.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = listAdapter
        }
        refreshLayout.setOnRefreshListener {
            loadError.visibility = View.GONE
            loader.visibility = View.VISIBLE
            // listAdapter.temp=false
            viewmodel.postRegisterDetails(Register(actionId, RegResp(uname, uid?.toLong()), null))

            //viewmodel.postRegisterDetails(Register(actionId, RegResp("",1)))


            refreshLayout.isRefreshing = false
        }


    }


    //    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//    }
    fun adapterClick(item: RegResp?, view: View) {
        userView = view
        val prefs = context?.let { SharedPreferenceHelper(it) }
        val uid = prefs?.getUID()
        val clickedUserId = item?.userId?.toInt()
        val userID = uid?.toInt()
        val chatBtwValue: String

        if (userID!! > clickedUserId!!) {
            chatBtwValue = """${clickedUserId}_$userID"""
        } else {
            chatBtwValue = """${userID}_$clickedUserId"""
        }


        viewmodel.userChatPageDetails(
            Register(
                106,
                null,
                chatConversationModel = ChatInputModel(chatBtwValue)
            )
        )
        viewmodel.userclickResponse.observe(this, myChatPageUserClickDetailsObserver)

    }


}