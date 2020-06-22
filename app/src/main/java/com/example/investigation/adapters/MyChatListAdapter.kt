package com.example.investigation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.investigation.R
import com.example.investigation.model.RegResp
import kotlinx.android.synthetic.main.local_chat_list_item.view.*

class MyChatListAdapter(private var userList:ArrayList<RegResp>):RecyclerView.Adapter<MyChatListAdapter.MyChatListViewHolder>() {

    fun updateMyChatList(newUserList:List<RegResp>){
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }
    class MyChatListViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChatListViewHolder {
        val inflator=LayoutInflater.from(parent.context)
        val view=inflator.inflate(R.layout.local_chat_list_item,parent,false)
        return MyChatListViewHolder(view)

    }

    override fun getItemCount()=userList.size

    override fun onBindViewHolder(holder: MyChatListViewHolder, position: Int) {
        holder.view.userName.text=userList[position].userName

    }
}