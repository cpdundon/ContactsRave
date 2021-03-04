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
import com.example.contactsrave.model.constant.addressType.EnumAddressType
import com.example.contactsrave.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.fragment_address_edit.*

@ExperimentalStdlibApi
class AddressEditFragment : Fragment() {
    private val TAG = "AddressEditFragment"
    private lateinit var binding: FragmentAddressEditBinding
    private val viewModel = ContactViewModel()
    private val arguments by navArgs<AddressEditFragmentArgs>()
    private lateinit var contact: Contact
    private var address = Address("","","","", EnumAddressType.Home)
    private var contactId = -1L

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

        contactId = arguments.contactId

        setUpObservers()
        setUpListeners()

        viewModel.getContact(contactId, requireContext())
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

        viewModel.getContact(contactId, requireContext()).observe(viewLifecycleOwner,
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
                street_one = it.etAddressOne.text.toString()
                street_two = it.etAddressTwo.text.toString()
                state = it.etAddressState.text.toString()
                zip_code = it.etZipCode.text.toString()

                address.type = when {
                    it.radioHome.isChecked -> EnumAddressType.Home
                    it.radioMailing.isChecked -> EnumAddressType.Mailing
                    it.radioSummer.isChecked -> EnumAddressType.Summer
                    else -> EnumAddressType.Work
                }
            }
        }

        viewModel.updateContact(contact, requireContext())
    }

    private fun loadInfo() {
        address.let{
            binding.apply {
                this.etAddressOne.setText(it.street_one)
                this.etAddressTwo.setText(it.street_two)
                this.etAddressState.setText(it.state)
                this.etZipCode.setText(it.zip_code)
                when(it.type) {
                    EnumAddressType.Mailing -> binding.radioMailing.isChecked = true
                    EnumAddressType.Summer -> binding.radioSummer.isChecked = true
                    EnumAddressType.Work -> binding.radioWork.isChecked = true
                    else -> binding.radioHome.isChecked = true
                    }
                }
            }
        }
    }
