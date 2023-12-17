package com.powersoft.foodiepal_culinarycompanion.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityAddRecipeBinding
import com.powersoft.foodiepal_culinarycompanion.models.Recipe

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddRecipeBinding
    private var photoUrl: Uri? = null
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                photoUrl = uri
                Glide.with(this)
                    .load(photoUrl)
                    .into(b.imgRecipe)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.imgBack.setOnClickListener { super.onBackPressed() }

        b.imgRecipe.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        b.btnAddRecipe.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val recipeName = b.etRecipeName.text.toString()
            val time = b.etTime.text.toString()
            val rating = b.ratingBar.rating

            val recipe = Recipe(recipeName, time, 0, rating, photoUrl.toString())

            val intent = Intent()
            intent.putExtra("recipe", recipe)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun validate(): Boolean {
        if (photoUrl == null) {
            Toast.makeText(this, "Recipe image is required!!", Toast.LENGTH_SHORT).show()
            return false
        } else if (b.etRecipeName.text.isEmpty()) {
            b.etRecipeName.error = "Recipe Name is required!!"
            b.etRecipeName.requestFocus()
            return false
        } else if (b.etTime.text.isEmpty()) {
            b.etTime.error = "Time is required!!"
            b.etTime.requestFocus()
            return false
        }
        return true
    }
}