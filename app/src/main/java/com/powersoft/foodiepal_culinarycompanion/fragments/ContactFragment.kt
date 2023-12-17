package com.powersoft.foodiepal_culinarycompanion.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.powersoft.foodiepal_culinarycompanion.R
import com.powersoft.foodiepal_culinarycompanion.adapters.ContactAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentContactBinding
import com.powersoft.foodiepal_culinarycompanion.models.Contact
import com.powersoft.foodiepal_culinarycompanion.utils.ItemClickListener

class ContactFragment : Fragment() {

    private lateinit var b: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentContactBinding.bind(
            inflater.inflate(
                R.layout.fragment_contact,
                container,
                false
            )
        )
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContactAdapter(getContactData())

        adapter.setItemClickListener(object : ItemClickListener {
            override fun onItemClicked(pos: Int) {
                when (pos) {
                    1 -> openDialer()
                    3 -> openEmail()
                }
            }
        })

        b.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun openEmail() {
        val recipientEmail = "romankhadka@gmail.com"
        val subject = ""
        val body = ""

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipientEmail")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }

        startActivity(emailIntent)
    }

    private fun openDialer() {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:6412339685")
        startActivity(dialIntent)
    }

    fun getContactData(): List<Contact> {
        return listOf(
            Contact(
                R.drawable.linkedin,
                "LinkedIn Profile",
                "https://www.linkedin.com/in/roman-khadka"
            ),
            Contact(R.drawable.ic_phone, "Phone", "+1 641 233 9685"),
            Contact(R.drawable.ic_location, "Address", "Saratoga Spring, UT, USA"),
            Contact(R.drawable.ic_email, "Email", "romankhadka@gmail.com"),
        )
    }
}