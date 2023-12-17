package com.powersoft.foodiepal_culinarycompanion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.powersoft.foodiepal_culinarycompanion.R
import com.powersoft.foodiepal_culinarycompanion.adapters.MealPlannerAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.DialogMealBinding
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentMealPlannerBinding
import com.powersoft.foodiepal_culinarycompanion.models.MealPlan
import com.powersoft.foodiepal_culinarycompanion.utils.ItemClickListener

class MealPlannerFragment : Fragment() {

    private lateinit var b: FragmentMealPlannerBinding
    private lateinit var adapter: MealPlannerAdapter
    private var mealPlanList = mutableListOf<MealPlan>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentMealPlannerBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateSampleMealPlan()

        adapter = MealPlannerAdapter(mealPlanList)
        b.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerView.adapter = adapter

        setupMealPlannerScheduleListener()
    }

    private fun setupMealPlannerScheduleListener() {
        adapter.setItemClickListener(object : ItemClickListener {
            override fun onItemClicked(pos: Int) {
                showDialog(pos)
            }
        })
    }

    private fun showDialog(pos: Int) {
        val dialog = AlertDialog.Builder(
            requireActivity()
        ).create()
        val b: DialogMealBinding = DialogMealBinding.inflate(
            LayoutInflater.from(context),
            null,
            false
        )
        dialog.setView(b.root)
        b.imgClose.setOnClickListener { v -> dialog.dismiss() }

        b.tvBreakfast.setOnClickListener {
            changeSuccess("Breakfast", dialog, pos)
        }
        b.tvLunch.setOnClickListener {
            changeSuccess("Lunch", dialog, pos)
        }
        b.tvDinner.setOnClickListener {
            changeSuccess("Dinner", dialog, pos)
        }

        dialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        dialog.show()
    }

    private fun changeSuccess(mealName: String, dialog: AlertDialog, pos: Int){
        mealPlanList[pos].mealName = mealName
        adapter.notifyItemChanged(pos)
        dialog.dismiss()
        Toast.makeText(requireActivity(), "Meal changed $mealName.", Toast.LENGTH_SHORT).show()
    }

    private fun generateSampleMealPlan() {
        mealPlanList.add(MealPlan("Sunday", "Breakfast"))
        mealPlanList.add(MealPlan("Monday", "Lunch"))
        mealPlanList.add(MealPlan("Tuesday", "Dinner"))
        mealPlanList.add(MealPlan("Wednesday", "Breakfast"))
        mealPlanList.add(MealPlan("Thursday", "Breakfast"))
        mealPlanList.add(MealPlan("Friday", "Lunch"))
        mealPlanList.add(MealPlan("Saturday", "Dinner"))
    }
}