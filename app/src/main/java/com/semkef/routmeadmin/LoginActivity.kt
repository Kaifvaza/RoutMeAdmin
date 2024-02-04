package com.semkef.routmeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.semkef.routmeadmin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btn.setOnClickListener {

            val userName = binding.userName.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (userName == "admin" && password == "admin"){
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            else if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            else{
                Toast.makeText(this, "Invalid user name or password", Toast.LENGTH_SHORT).show()
            }

        }

        binding.showPassword.setOnCheckedChangeListener { buttonView, isChecked ->

            val password = binding.password

            if (isChecked) {
                password.transformationMethod = null // Show password
            } else {
                password.transformationMethod = PasswordTransformationMethod() // Hide password
            }
        }

    }
}