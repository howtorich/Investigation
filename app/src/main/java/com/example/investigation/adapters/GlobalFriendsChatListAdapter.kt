package com.example.investigation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.investigation.R
import com.example.investigation.databinding.AllFriendsFriendListItemBinding
import com.example.investigation.model.RegResp
import java.util.*

class GlobalFriendsChatListAdapter(
    private var userList: ArrayList<RegResp>,

    val context: Fragment,
    @NonNull private val adapterClick: ((uid: Long?, position: Int) -> Unit)? = null
) :
    RecyclerView.Adapter<GlobalFriendsChatListAdapter.GlobalFrinedsChatListViewHolder>() {
    lateinit var view: AllFriendsFriendListItemBinding
    private var selectedItemPosition: Int = -1

    fun setSelectedposition(position: Int) {
        selectedItemPosition = position
    }

    fun updateMyChatList(newUserList: List<RegResp>) {
        userList.clear()
        userList.addAll(newUserList)
        selectedItemPosition = -1
        // userList.removeAt(0)
        notifyDataSetChanged()
    }

    //val adapterClick:(uid:Long?)->Unit
    class GlobalFrinedsChatListViewHolder(var view: AllFriendsFriendListItemBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GlobalFrinedsChatListViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        //val view: AllFriendsFriendListItemBinding =
        view =
            DataBindingUtil.inflate(inflater, R.layout.all_friends_friend_list_item, parent, false)
        return GlobalFrinedsChatListViewHolder(view)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: GlobalFrinedsChatListViewHolder, position: Int) {

        //holder.view.userName.text=userList[position].userName
        holder.view.allFriendsUser = userList[position]
        //holder.view.allFrinedsButtonListener=this
        holder.view.addfriendsButton.setOnClickListener {

            this.adapterClick?.invoke(userList[position].userId, position)

        }
        if (position == selectedItemPosition) {
            holder.view.addfriendsButton.visibility = View.GONE
            holder.view.message.visibility = View.VISIBLE
        } else {
            holder.view.addfriendsButton.visibility = View.VISIBLE
            holder.view.message.visibility = View.GONE
        }
    }


}