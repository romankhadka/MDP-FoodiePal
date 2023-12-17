package com.powersoft.foodiepal_culinarycompanion.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.powersoft.foodiepal_culinarycompanion.databinding.ItemRecipeBinding
import com.powersoft.foodiepal_culinarycompanion.models.Recipe

class RecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.foodName.text = recipe.foodName
            binding.tvTime.text = recipe.foodDuration
            if (recipe.imagePath == null) {
                binding.imgFood.setImageResource(recipe.image)
            }else{
                binding.imgFood.setImageURI(Uri.parse(recipe.imagePath))
            }
            binding.ratingBar.rating = recipe.userRating
            binding.tvRating.text = recipe.userRating.toString()
        }
    }
}