package com.powersoft.foodiepal_culinarycompanion.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityLoginBinding
import com.powersoft.foodiepal_culinarycompanion.models.User
import com.powersoft.foodiepal_culinarycompanion.utils.PreferenceManager

class LoginActivity : AppCompatActivity() {

    private lateinit var b: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnLogin.setOnClickListener {
            login()
        }

        b.viewSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        if (!validate()) return
        val email = b.etEmail.text.toString()
        val password = b.etPassword.text.toString()

        val preferenceManager = PreferenceManager.getInstance(this)
        val user = preferenceManager.loginUser(email, password)
        if (user != null) {
            preferenceManager.setLoggedIn(true)
            navigateToMainScreen(user)
        } else {
            Toast.makeText(this, "Email or Password is incorrect!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMainScreen(user: User) {
        finish()
        val intent = Intent().apply {
            putExtra("user", Gson().toJson(user))
            setClass(this@LoginActivity, MainActivity::class.java)
        }
        startActivity(intent)
    }

    private fun validate(): Boolean {
        if (b.etEmail.text.isEmpty()) {
            b.etEmail.error = "Email is required!!"
            b.etEmail.requestFocus()
            return false
        } else if (!b.etEmail.text.contains("@")) {
            b.etEmail.error = "Invalid email!!"
            b.etEmail.requestFocus()
            return false
        } else if (b.etPassword.text.isEmpty()) {
            b.etPassword.error = "Password is required!!"
            b.etPassword.requestFocus()
            return false
        }
        return true
    }
}