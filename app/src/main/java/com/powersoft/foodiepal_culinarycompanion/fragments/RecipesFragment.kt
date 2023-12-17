package com.powersoft.foodiepal_culinarycompanion.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.powersoft.foodiepal_culinarycompanion.R
import com.powersoft.foodiepal_culinarycompanion.activities.AddRecipeActivity
import com.powersoft.foodiepal_culinarycompanion.adapters.RecipeAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentRecipesBinding
import com.powersoft.foodiepal_culinarycompanion.models.Recipe

class RecipesFragment : Fragment() {

    private lateinit var b: FragmentRecipesBinding
    private lateinit var adapter: RecipeAdapter
    private var recipeList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentRecipesBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateSampleRecipe()
        adapter = RecipeAdapter(recipeList)

        b.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerView.adapter = adapter

        val contract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK) {
                    val recipe = it.data?.getParcelableExtra("recipe") as? Recipe
                    if (recipe != null) {
                        recipeList.add(recipe)
                    }
                    adapter.notifyItemChanged(recipeList.size)
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireActivity(), "Cancelled by User", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        b.fabAdd.setOnClickListener {
            contract.launch(Intent(requireActivity(), AddRecipeActivity::class.java))
        }

    }

    private fun generateSampleRecipe() {
        recipeList.add(Recipe("Vegetable Noodle", "12 Mins", R.drawable.ic_noodle, 4.5f))
        recipeList.add(Recipe("Spaghetti", "30 Mins", R.drawable.ic_spaghetti, 4.8f))
        recipeList.add(Recipe("Chicken Spaghetti", "32 Mins", R.drawable.ic_chicken_spaghetti, 5f))
        recipeList.add(Recipe("Pasta", "15 Mins", R.drawable.ic_pasta, 4.3f))
    }
}