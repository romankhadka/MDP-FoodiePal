package com.powersoft.foodiepal_culinarycompanion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.powersoft.foodiepal_culinarycompanion.databinding.ItemMealPlannerBinding
import com.powersoft.foodiepal_culinarycompanion.models.MealPlan
import com.powersoft.foodiepal_culinarycompanion.utils.ItemClickListener

class MealPlannerAdapter(private val mealPlanList: List<MealPlan>) :
    RecyclerView.Adapter<MealPlannerAdapter.MealPlannerViewHolder>() {

    private var itemClickListener:  ItemClickListener? = null

    inner class MealPlannerViewHolder(private val b: ItemMealPlannerBinding) :
        RecyclerView.ViewHolder(b.root) {

        init {
            b.root.setOnClickListener{
                itemClickListener?.onItemClicked(adapterPosition)
            }
        }

        fun bind(mealPlan: MealPlan) {
            b.tvDay.text = mealPlan.day
            b.tvMealName.text = mealPlan.mealName
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlannerViewHolder {
        return MealPlannerViewHolder(
            ItemMealPlannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealPlannerViewHolder, position: Int) {
        val mealPlanItem = mealPlanList[position]
        holder.bind(mealPlanItem)
    }

    override fun getItemCount(): Int {
        return mealPlanList.size
    }
}
