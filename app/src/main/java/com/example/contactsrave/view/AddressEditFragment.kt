package com.example.contactsrave.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.contactsrave.databinding.FragmentAddEditContactBinding
import com.example.contactsrave.databinding.FragmentAddressEditBinding
import com.example.contactsrave.model.Address
import com.example.contactsrave.model.Contact
import com.example.contactsrave.viewmodel.ContactViewModel

@ExperimentalStdlibApi
class AddressEditFragment : Fragment() {
    private val TAG = "AddressEditFragment"
    private lateinit var binding: FragmentAddressEditBinding
    private val viewModel = ContactViewModel()
    private val arguments by navArgs<AddressEditFragmentArgs>()
    private lateinit var contact: Contact
    private lateinit var address: Address

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddressEditBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactId = arguments.contactId

        setUpObservers()
        setUpListeners()

        viewModel.fetchContact(contactId, requireContext())
    }

    private fun setUpListeners() {
        binding.btnApply.setOnClickListener(View.OnClickListener {
            saveInformation()
        })
    }

    private fun setUpObservers() {
        viewModel.updateRtn.observe(viewLifecycleOwner,
            Observer<Long> {
                Log.i(TAG, "setUpObservers: return from viewModel -> $it")
            })

        viewModel.contact.observe(viewLifecycleOwner,
            Observer<Contact> {
                contact = it

                if (arguments.addressId == -1L) {
                    address = Address(null, null, null, null)
                    contact.addresses += address
                    address.id = contact.addresses.size - 1L
                } else {
                    it.addresses.forEach { add ->
                        if (add.id == arguments.addressId) {
                            this.address = add
                        }
                    }
                }

                loadInfo()
                Log.i(TAG, "setUpObservers: return from get contact in viewModel -> ${address.id} ${address.street_one.toString()}"
                )
            }
        )
    }


    private fun saveInformation(){
        binding.let{
            address.apply{
                this.street_one = it.etAddressOne.text.toString()
            }
        }

        viewModel.updateContact(contact, requireContext())
    }

    private fun loadInfo() {
        address.let{
            binding.apply {
                this.etAddressOne.setText(it.street_one)
            }
        }
    }
}