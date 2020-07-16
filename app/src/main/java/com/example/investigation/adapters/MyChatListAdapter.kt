package com.example.investigation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.investigation.R
import com.example.investigation.databinding.LocalChatListItemBinding
import com.example.investigation.model.RegResp
import com.example.investigation.view.MyChatListPageFragmentDirections
import com.example.investigation.view.UserClickListener
import kotlinx.android.synthetic.main.add_friends_button_layout.view.*
import kotlinx.android.synthetic.main.local_chat_list_item.view.*

class MyChatListAdapter(private var userList:ArrayList<RegResp>, @NonNull private val adapterClick:((item:RegResp?,view:View)->Unit)?=null):
    RecyclerView.Adapter<MyChatListAdapter.MyChatListViewHolder>(){

    //var temp=false
    fun updateMyChatList(newUserList:List<RegResp>){
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }
    class MyChatListViewHolder(var view:LocalChatListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChatListViewHolder {
        val inflator = LayoutInflater.from(parent.context)

//       if(!temp){
//           temp=true
//            val view = inflator.inflate(R.layout.add_friends_button_layout, parent, false)
//            return MyChatListViewHolder(view)
//        } else {
            val view = DataBindingUtil.inflate<LocalChatListItemBinding>(inflator, R.layout.local_chat_list_item,parent,false)
            return MyChatListViewHolder(view)
        //}
    }

    override fun getItemCount()=userList.size

    override fun onBindViewHolder(holder: MyChatListViewHolder, position: Int) {
        //if(position==0){

//            holder.view.addfriends_button.setOnClickListener{
//                val action= MyChatListPageFragmentDirections.actionGlobalchat()
//                temp=false
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
           // else {
        holder.view.user=userList[position]
        holder.view.myFriendClick.setOnClickListener {
            this.adapterClick?.invoke(userList[position],it)
        }

           }


}