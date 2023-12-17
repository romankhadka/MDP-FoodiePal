package com.powersoft.foodiepal_culinarycompanion.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.powersoft.foodiepal_culinarycompanion.databinding.ItemBlogPostBinding
import com.powersoft.foodiepal_culinarycompanion.models.BlogPost

class BlogAdapter(private val blogPostList: List<BlogPost>) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    class BlogViewHolder(var b: ItemBlogPostBinding) : RecyclerView.ViewHolder(b.root) {

        fun bind(blogPost: BlogPost) {
            b.tvTitle.text = blogPost.title
            b.tvContent.text = blogPost.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val b = ItemBlogPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogViewHolder(b)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogPost = blogPostList[position]
        holder.bind(blogPost)
        holder.b.btnShare.setOnClickListener {
            shareBlogPost(blogPost, holder.b.root.context)
        }
    }

    private fun shareBlogPost(blogPost: BlogPost, context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, blogPost.title)
            putExtra(Intent.EXTRA_TEXT, "${blogPost.title}\n${blogPost.content}")
        }

        val chooserIntent = Intent.createChooser(shareIntent, null)
        context.startActivity(chooserIntent)
    }

    override fun getItemCount(): Int {
        return blogPostList.size
    }
}
