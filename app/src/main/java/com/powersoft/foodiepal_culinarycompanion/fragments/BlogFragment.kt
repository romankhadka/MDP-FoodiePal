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
import com.powersoft.foodiepal_culinarycompanion.activities.AddBlogActivity
import com.powersoft.foodiepal_culinarycompanion.adapters.BlogAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentBlogBinding
import com.powersoft.foodiepal_culinarycompanion.models.BlogPost

class BlogFragment : Fragment() {

    private lateinit var b: FragmentBlogBinding
    private lateinit var blogAdapter: BlogAdapter
    private var blogList = mutableListOf<BlogPost>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentBlogBinding.bind(inflater.inflate(R.layout.fragment_blog, container, false))
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateSampleBlogPost()
        blogAdapter = BlogAdapter(blogList)
        b.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerView.adapter = blogAdapter

        val contract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK) {
                    val blogPost = it.data?.getParcelableExtra("blogPost") as? BlogPost
                    if (blogPost != null) {
                        blogList.add(blogPost)
                    }
                    blogAdapter.notifyItemChanged(blogList.size)
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireActivity(), "Cancelled by User", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        b.fabAdd.setOnClickListener {
            contract.launch(Intent(requireActivity(), AddBlogActivity::class.java))
        }
    }

    private fun generateSampleBlogPost() {
        blogList.add(
            BlogPost(
                "The Verge",
                "The Verge is a blog focused on examining how technology will change the future."
            )
        )
        blogList.add(
            BlogPost(
                "Engadget",
                "Launched by Peter Rojas, Engadget is a technology blog providing reviews of gadgets and consumer electronics as well as the latest news in the tech world."
            )
        )
        blogList.add(
            BlogPost(
                "Gizmodo",
                "Gizmodo is another technology blog created by Peter Rojas. The blog publishes news and opinion pieces on technology, gadgets, science, entertainment, culture, and the environment."
            )
        )
    }
}