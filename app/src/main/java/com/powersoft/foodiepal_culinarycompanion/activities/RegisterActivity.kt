package com.powersoft.foodiepal_culinarycompanion.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityRegisterBinding
import com.powersoft.foodiepal_culinarycompanion.models.User
import com.powersoft.foodiepal_culinarycompanion.utils.PreferenceManager

class RegisterActivity : AppCompatActivity() {

    private lateinit var b: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        b = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.btnSignUp.setOnClickListener {
            register()
        }

        b.viewAlreadyHaveAccount.setOnClickListener {
            super.onBackPressed()
        }

        b.fabBack.setOnClickListener{
            super.onBackPressed()
        }
    }

    private fun register() {
        if (!validate()) return
        val email = b.etEmail.text.toString()
        val name = b.etName.text.toString()
        val password = b.etPassword.text.toString()

        val preferenceManager = PreferenceManager.getInstance(this)
        val user = User(email, password, name)
        preferenceManager.saveUser(user)
        preferenceManager.setLoggedIn(true)
        navigateToMainScreen(user)
    }

    private fun navigateToMainScreen(user: User) {
        finishAffinity()
        val intent = Intent().apply {
            putExtra("user", Gson().toJson(user))
            setClass(this@RegisterActivity, MainActivity::class.java)
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
        } else if (b.etName.text.isEmpty()) {
            b.etName.error = "Name is required!!"
            b.etName.requestFocus()
            return false
        }else if (b.etPassword.text.isEmpty()) {
            b.etPassword.error = "Password is required!!"
            b.etPassword.requestFocus()
            return false
        }else if (!b.etConfirmPassword.text.toString().equals(b.etPassword.text.toString())) {
            b.etConfirmPassword.error = "Confirm password mismatched!!"
            b.etConfirmPassword.requestFocus()
            return false
        }
        return true
    }
}