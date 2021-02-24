package com.example.contactsrave.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsrave.databinding.FragmentAddressCardBinding
import com.example.contactsrave.model.Address
import com.example.contactsrave.model.Contact
import com.example.contactsrave.model.constant.addressType.EnumAddressType
import com.example.contactsrave.model.constant.addressType.Home
import com.example.contactsrave.model.constant.addressType.Mailing
import com.example.contactsrave.model.constant.addressType.Summer
import com.example.contactsrave.view.AddEditContactDirections

class AddressAdapter(private val contact: Contact) :
    RecyclerView.Adapter<AddressAdapter.AddressesViewHolder>() {
    private val TAG = "AddressAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressesViewHolder {

        val binding: FragmentAddressCardBinding = FragmentAddressCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AddressesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contact.addresses.size
    }

    override fun onBindViewHolder(holder: AddressesViewHolder, position: Int) {
            holder.setAddress(contact, position)
    }

    class AddressesViewHolder(private val binding: FragmentAddressCardBinding) : RecyclerView.ViewHolder(binding.root) {
        private val TAG = "AddressesViewHolder"

        fun setAddress(contact: Contact, position: Int) {
            contact.addresses[position].let{
                binding.apply {
                    tvZipCode.text = it.zip_code
                    tvAddressOne.text = it.street_one
                    val typeID = when (it.type) {
                        EnumAddressType.Home -> "H -> "
                        EnumAddressType.Mailing -> "M -> "
                        EnumAddressType.Summer -> "S -> "
                        else -> "W -> "
                    }
                    tvAddressType.text = typeID
                }
                setListeners(contact, it)
            }
        }

        private fun setListeners(contact: Contact, address: Address) {
            binding.constraintAddressCard.setOnClickListener(View.OnClickListener { goToDetailActivity(contact, address) })
        }

        private fun goToDetailActivity(contact: Contact, address: Address) {
            val action = AddEditContactDirections.actionAddEditContactToAddressEditFragment(contact.id, address.id)
            binding.constraintAddressCard.findNavController().navigate(action)
        }
    }
}
