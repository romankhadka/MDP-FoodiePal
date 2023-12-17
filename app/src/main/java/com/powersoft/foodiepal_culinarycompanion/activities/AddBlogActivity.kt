package com.powersoft.foodiepal_culinarycompanion.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityAddBlogBinding
import com.powersoft.foodiepal_culinarycompanion.models.BlogPost

class AddBlogActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddBlogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityAddBlogBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.imgBack.setOnClickListener { super.onBackPressed() }


        b.btnAdd.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val title = b.etTitle.text.toString()
            val content = b.etBlogContent.text.toString()

            val blogPost = BlogPost(title, content)

            val intent = Intent()
            intent.putExtra("blogPost", blogPost)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun validate(): Boolean {
        if (b.etTitle.text.isEmpty()) {
            b.etTitle.error = "Title is required!!"
            b.etTitle.requestFocus()
            return false
        } else if (b.etBlogContent.text.isEmpty()) {
            b.etBlogContent.error = "Blog content is required!!"
            b.etBlogContent.requestFocus()
            return false
        }
        return true
    }
}