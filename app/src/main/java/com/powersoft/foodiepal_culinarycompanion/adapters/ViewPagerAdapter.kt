package com.powersoft.foodiepal_culinarycompanion.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.powersoft.foodiepal_culinarycompanion.fragments.AboutMeFragment
import com.powersoft.foodiepal_culinarycompanion.fragments.BlogFragment
import com.powersoft.foodiepal_culinarycompanion.fragments.ContactFragment
import com.powersoft.foodiepal_culinarycompanion.fragments.MealPlannerFragment
import com.powersoft.foodiepal_culinarycompanion.fragments.RecipesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecipesFragment()
            1 -> MealPlannerFragment()
            2 -> BlogFragment()
            3 -> ContactFragment()
            4 -> AboutMeFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}