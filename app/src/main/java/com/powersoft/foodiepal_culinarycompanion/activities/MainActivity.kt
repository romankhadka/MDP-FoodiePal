package com.powersoft.foodiepal_culinarycompanion.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.powersoft.foodiepal_culinarycompanion.R
import com.powersoft.foodiepal_culinarycompanion.adapters.ViewPagerAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityMainBinding
import com.powersoft.foodiepal_culinarycompanion.utils.PreferenceManager


class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private lateinit var preferenceManager: PreferenceManager
    private val tabs = arrayOf("Recipes", "Meal Planner", "Blog", "Contact", "About Me")
    private val icons = arrayOf(
        R.drawable.ic_recipe,
        R.drawable.ic_meal_planner,
        R.drawable.ic_blog,
        R.drawable.ic_contact,
        R.drawable.ic_about
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        preferenceManager = PreferenceManager.getInstance(this)

        if (!preferenceManager.isLoggedIn()) {
            startLoginActivity()
        }
        setupTabLayout()
    }

    private fun setupTabLayout() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        b.viewPager.adapter = adapter

        TabLayoutMediator(b.tabLayout, b.viewPager) { tab, position ->
            tab.text = tabs[position]
            tab.icon = ResourcesCompat.getDrawable(resources, icons[position], null)
        }.attach()

        b.viewPager.offscreenPageLimit = 5
    }

    private fun startLoginActivity() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}