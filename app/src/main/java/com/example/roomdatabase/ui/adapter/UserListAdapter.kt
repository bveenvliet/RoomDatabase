package com.example.roomdatabase.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.roomdatabase.R
import com.example.roomdatabase.data.User

class UserListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>() // Cached copy of users

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstNameItemView: TextView = itemView.findViewById(R.id.tvFirstName)
        val lastNameItemView: TextView = itemView.findViewById(R.id.tvLastName)
        val urlImageItemView: ImageView = itemView.findViewById(R.id.ivAvatar)
        val clUserItem: ConstraintLayout = itemView.findViewById(R.id.clUserItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.run {
            firstNameItemView.text = current.firstName
            lastNameItemView.text = current.lastName
            urlImageItemView.load(current.url)
            clUserItem.setBackgroundColor(if (position % 2 == 0) Color.WHITE else Color.LTGRAY)
        }
    }

    override fun getItemCount(): Int = users.size

    internal fun setUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

}