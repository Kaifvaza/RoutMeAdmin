package com.semkef.routmeadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.semkef.routmeadmin.R
import com.semkef.routmeadmin.model.UserItem

class UserListAdapter(context: Context, private val resource: Int, private val userList: MutableList<UserItem>) :
    ArrayAdapter<UserItem>(context, resource, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val currentUser = userList[position]

        itemView.findViewById<TextView>(R.id.nameTextView).text = currentUser.fullName

        itemView.findViewById<TextView>(R.id.mobileNumberTextView).text = currentUser.mobileNumber
        itemView.findViewById<ImageView>(R.id.profileImageView)

        // Load profile image using Glide library (add Glide dependency in your app's build.gradle)
        itemView.findViewById<ImageView>(R.id.profileImageView).apply {
            Glide.with(context)
                .load(currentUser.profileImageUrl)
                .placeholder(R.drawable.default_profile_image) // Placeholder image
                .circleCrop()
                .into(this)
        }

        return itemView
    }
}
