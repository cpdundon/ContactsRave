package com.example.contactsrave.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsrave.databinding.FragmentContactBinding
import com.example.contactsrave.model.Contact
import com.example.contactsrave.view.SelectContactFragmentDirections

class ContactsAdapter(private val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {

        val binding: FragmentContactBinding = FragmentContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact: Contact = contacts[position]
        holder.setContact(contact)
    }

    class ContactsViewHolder(private val binding: FragmentContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setContact(contact: Contact) {
            contact.let{
                binding.apply {
                    tvFirstName.text = it.first_name
                    tvLastName.text = it.last_name
                    var first = it.first_name.toString()
                    if (first.isNotEmpty()) {
                        first = first[0].toString()
                    }
                    var last = it.last_name.toString()
                    if (last.isNotEmpty()) {
                        last = last[0].toString()
                    }

                    tvInitials.text = "$first$last -> "
                }
            }
            setListeners(contact)
        }

        private fun setListeners(contact: Contact) {
            binding.contactFragment.setOnClickListener(View.OnClickListener { goToDetailActivity(contact) })
        }

        private fun goToDetailActivity(contact: Contact) {
            val action = SelectContactFragmentDirections.actionSelectContactFragmentToAddEditContact(contact.id)
            binding.contactFragment.findNavController().navigate(action)
        }

    }

}
