package com.example.investigation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.investigation.R
import com.example.investigation.model.RegResp
import kotlinx.android.synthetic.main.all_friends_friend_list_item.view.*
import java.util.ArrayList

class GlobalFriendsChatListAdapter(private var userList:ArrayList<RegResp>, @NonNull private val adapterClick:((uid:Long?)-> Unit)?=null):RecyclerView.Adapter<GlobalFriendsChatListAdapter.GlobalFrinedsChatListViewHolder>() {

    fun updateMyChatList(newUserList:List<RegResp>){
        userList.clear()
        userList.addAll(newUserList)
        userList.removeAt(0)
        notifyDataSetChanged()
    }
    //val adapterClick:(uid:Long?)->Unit
    class GlobalFrinedsChatListViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalFrinedsChatListViewHolder {
      val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.all_friends_friend_list_item,parent,false)
        return GlobalFrinedsChatListViewHolder(view)
    }

    override fun getItemCount()=userList.size

    override fun onBindViewHolder(holder: GlobalFrinedsChatListViewHolder, position: Int) {
       holder.view.userName.text=userList[position].userName

       holder.view.addfriends_button.setOnClickListener {
           this.adapterClick?.invoke(userList[position].userId)
           userList.removeAt(position)
           notifyDataSetChanged()

        }
    }


}