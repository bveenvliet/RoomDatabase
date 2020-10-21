package com.example.roomdatabase.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.roomdatabase.data.User
import com.example.roomdatabase.databinding.UserItemBinding

class UserListAdapter internal constructor(
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var users = emptyList<User>() // Cached copy of users

    class UserViewHolder(private val itemBinding: UserItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: User) {
            itemBinding.run {
                ivAvatar.load(user.url)
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                clUserItem.setBackgroundColor(if (adapterPosition % 2 == 0) Color.WHITE else Color.LTGRAY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int = users.size

    internal fun setUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

}