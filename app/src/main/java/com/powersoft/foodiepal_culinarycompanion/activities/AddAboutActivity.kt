package com.powersoft.foodiepal_culinarycompanion.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityAddAboutBinding
import com.powersoft.foodiepal_culinarycompanion.models.About

class AddAboutActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityAddAboutBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.imgBack.setOnClickListener { super.onBackPressed() }


        b.btnAdd.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val culinaryJourney = b.etCulinaryJourney.text.toString()
            val favoriteRecipe = b.etFavoriteRecipe.text.toString()
            val foodPhilosophy = b.etFoodPhilosophy.text.toString()

            val about = About(culinaryJourney, favoriteRecipe, foodPhilosophy)

            val intent = Intent()
            intent.putExtra("about", about)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun validate(): Boolean {
        if (b.etCulinaryJourney.text.isEmpty()) {
            b.etCulinaryJourney.error = "Culinary Journey is required!!"
            b.etCulinaryJourney.requestFocus()
            return false
        } else if (b.etFavoriteRecipe.text.isEmpty()) {
            b.etFavoriteRecipe.error = "Favorite Recipes is required!!"
            b.etFavoriteRecipe.requestFocus()
            return false
        } else if (b.etFoodPhilosophy.text.isEmpty()) {
            b.etFoodPhilosophy.error = "Food Philosophy is required!!"
            b.etFoodPhilosophy.requestFocus()
            return false
        }
        return true
    }
}