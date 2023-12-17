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
import com.powersoft.foodiepal_culinarycompanion.activities.AddAboutActivity
import com.powersoft.foodiepal_culinarycompanion.activities.LoginActivity
import com.powersoft.foodiepal_culinarycompanion.activities.RegisterActivity
import com.powersoft.foodiepal_culinarycompanion.adapters.AboutAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentAboutBinding
import com.powersoft.foodiepal_culinarycompanion.models.About
import com.powersoft.foodiepal_culinarycompanion.utils.PreferenceManager

class AboutMeFragment : Fragment() {

    private lateinit var b: FragmentAboutBinding
    private lateinit var adapter: AboutAdapter
    private var aboutList = mutableListOf<About>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentAboutBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateDummyAbout()
        adapter = AboutAdapter(aboutList)

        b.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerView.adapter = adapter

        val contract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK) {
                    val about = it.data?.getParcelableExtra("about") as? About
                    if (about != null) {
                        aboutList.add(about)
                    }
                    adapter.notifyItemChanged(aboutList.size)
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireActivity(), "Cancelled by User", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        b.fabAddDetails.setOnClickListener {
            contract.launch(Intent(requireActivity(), AddAboutActivity::class.java))
        }

        b.btnLogout.setOnClickListener {
            PreferenceManager.getInstance(requireActivity()).logout()
            requireActivity().finish()
            requireActivity().startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }
    }

    fun generateDummyAbout() {
        aboutList.add(
            About(
                "Write something about culinary journey here.",
                "In this section mention about your favorite recipe.",
                "Here you can talk about food philosophy"
            )
        )
    }
}