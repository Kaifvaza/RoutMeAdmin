package com.semkef.routmeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.semkef.routmeadmin.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.llManageUsers.setOnClickListener {
            startActivity(Intent(this, ManageUsersActivity::class.java))
        }

    }
}
