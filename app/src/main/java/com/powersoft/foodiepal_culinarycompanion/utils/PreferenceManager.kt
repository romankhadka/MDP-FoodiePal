package com.powersoft.foodiepal_culinarycompanion.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.powersoft.foodiepal_culinarycompanion.models.User

class PreferenceManager private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val PREF_NAME = "UserPrefs"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USERS = "users"

        // Singleton instance
        @Volatile
        private var instance: PreferenceManager? = null

        fun getInstance(context: Context): PreferenceManager {
            return instance ?: synchronized(this) {
                instance ?: PreferenceManager(context).also { instance = it }
            }
        }
    }

    fun saveUser(user: User) {
        val userList = getUsers()
        userList.add(user)

        val userJson = Gson().toJson(userList)
        editor.putString(KEY_USERS, userJson).apply()
    }

    private fun getUsers(): MutableList<User> {
        val userJson = sharedPreferences.getString(KEY_USERS, null)
        return if (userJson != null) {
            Gson().fromJson(userJson, object : TypeToken<MutableList<User>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    fun loginUser(email: String, password: String): User? {
        getUsers().forEach {
            if (email == it.email && password == it.password){
                return it
            }
        }
        return null
    }


    fun setLoggedIn(isLoggedIn: Boolean) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout() {
        editor.remove(KEY_IS_LOGGED_IN)
        editor.apply()
    }
}
