package com.powersoft.foodiepal_culinarycompanion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.powersoft.foodiepal_culinarycompanion.databinding.ContactListBinding
import com.powersoft.foodiepal_culinarycompanion.models.Contact
import com.powersoft.foodiepal_culinarycompanion.utils.ItemClickListener

class ContactAdapter(private val contactList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = contactList.size

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ContactViewHolder(private val b: ContactListBinding) :
        RecyclerView.ViewHolder(b.root) {

        init {
            b.root.setOnClickListener {
                itemClickListener?.onItemClicked(adapterPosition)
            }
        }

        fun bind(contact: Contact) {
            with(contact) {
                b.ivIcon.setImageResource(icon)
                b.tvTitle.text = title
                b.tvContent.text = content
            }
        }
    }
}