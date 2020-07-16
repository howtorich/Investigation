package com.example.investigation.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investigation.R
import com.example.investigation.adapters.GlobalFriendsChatListAdapter
import com.example.investigation.model.AddFriendModel
import com.example.investigation.model.AddingFriend

import com.example.investigation.model.RegResp
import com.example.investigation.model.Register
import com.example.investigation.utils.SharedPreferenceHelper
import com.example.investigation.viewmodel.MyChatListViewModel

import kotlinx.android.synthetic.main.fragment_global_chat_list.*
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


class GlobalChatListFragment : Fragment() {

    private lateinit var viewmodel: MyChatListViewModel
    private val listAdapter = GlobalFriendsChatListAdapter(
        arrayListOf(),
        this
    ) { uid: Long?, position: Int -> adapterClick(uid, position) }


    private val userListDataObserver = Observer<List<RegResp>> { list: List<RegResp> ->
        list.let {
            allfrineds_list.visibility = View.VISIBLE
            listAdapter.updateMyChatList(it)
        }
    }

    private val loadingLivedataObserver = Observer<Boolean> {
        loader.visibility = if (it) View.VISIBLE else View.GONE
        if (it) {
            errorMessage.visibility = View.GONE
            allfrineds_list.visibility = View.GONE
        }
    }

    private val errorLivedataobserver = Observer<Boolean> {
        errorMessage.visibility = if (it) View.VISIBLE else View.GONE
        if (it) {
            allfrineds_list.visibility = View.GONE
            loader.visibility = View.GONE
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_global_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "All Users"
        val prefs = context?.let { SharedPreferenceHelper(it) }

        viewmodel = ViewModelProviders.of(this).get(MyChatListViewModel::class.java)
        val actionId: Int = 103
        val uname = prefs?.getUName()
        val uid = prefs?.getUID()

        viewmodel.postRegisterDetails(Register(actionId, RegResp(uname, uid?.toLong()), null))
        //viewmodel.postRegisterDetails(Register(actionId, RegResp("",1)))

        viewmodel.users.observe(viewLifecycleOwner, userListDataObserver)
        viewmodel.loading.observe(viewLifecycleOwner, loadingLivedataObserver)
        viewmodel.loadError.observe(viewLifecycleOwner, errorLivedataobserver)

        allfrineds_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
        refreshLayout.setOnRefreshListener {
            errorMessage.visibility = View.GONE
            loader.visibility = View.VISIBLE
            viewmodel.postRegisterDetails(Register(actionId, RegResp(uname, uid?.toLong()), null))
            refreshLayout.isRefreshing = false
        }
    }

    fun adapterClick(item: Long?, position: Int) {

        viewmodel = ViewModelProviders.of(this).get(MyChatListViewModel::class.java)
        val prefs = context?.let { SharedPreferenceHelper(it) }

        val actionId: Int = 104
        val uid = prefs?.getUID()
        val frdUserId = item
        viewmodel.postAddFriendDetails(
            AddingFriend(
                actionId,
                AddFriendModel(uid?.toLong(), frdUserId)
            )
        )
        viewmodel.addFriendResult.observe(this, Observer<Boolean> {
            if (it) {
                context.let {
                    Toast.makeText(it, "Friend Request Sent", Toast.LENGTH_SHORT).show()
                    listAdapter.setSelectedposition(position)
                    listAdapter.notifyItemChanged(position)
                }
            }
        })

    }


}