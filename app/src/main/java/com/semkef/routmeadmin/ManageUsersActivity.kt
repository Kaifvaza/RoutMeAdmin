package com.semkef.routmeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.semkef.routmeadmin.adapter.UserListAdapter
import com.semkef.routmeadmin.databinding.ActivityManageUsersBinding
import com.semkef.routmeadmin.model.UserItem  // Import the correct UserItem class

class ManageUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageUsersBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userList: MutableList<UserItem>  // Use the correct UserItem class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        userList = mutableListOf()

        binding.userListView.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = userList[position]
            // Handle click on user item (e.g., open user details activity)
        }

        fetchUserData()
    }

    private fun fetchUserData() {
        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val user = userSnapshot.getValue(UserItem::class.java)  // Use the correct UserItem class
                        user?.let {
                            userList.add(user)
                        }
                    }
                    populateUserList()
                } else {
                    // Handle case where no users are found
                    Toast.makeText(this@ManageUsersActivity, "No users found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
                Toast.makeText(this@ManageUsersActivity, "Error fetching users: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        }

        databaseReference.addListenerForSingleValueEvent(userListener)
    }

    private fun populateUserList() {
        val adapter = UserListAdapter(this, R.layout.item_user, userList)
        binding.userListView.adapter = adapter
    }
}
