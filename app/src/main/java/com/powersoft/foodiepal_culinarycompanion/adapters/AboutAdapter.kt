package com.powersoft.foodiepal_culinarycompanion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.powersoft.foodiepal_culinarycompanion.databinding.ItemAboutBinding
import com.powersoft.foodiepal_culinarycompanion.models.About

class AboutAdapter(private val abouts: List<About>) :
    RecyclerView.Adapter<AboutAdapter.AboutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val binding =
            ItemAboutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AboutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.bind(abouts[position])
    }

    override fun getItemCount(): Int {
        return abouts.size
    }

    class AboutViewHolder(private val binding: ItemAboutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(about: About) {
            binding.tvCulinaryJourney.text = about.culinaryJourney
            binding.tvFavoriteRecipes.text = about.favoriteRecipe
            binding.tvFoodPhilosophy.text = about.foodPhilosophy
        }
    }
}