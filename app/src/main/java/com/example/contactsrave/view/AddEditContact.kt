package com.example.contactsrave.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsrave.R
import com.example.contactsrave.databinding.FragmentAddEditContactBinding
import com.example.contactsrave.model.Contact
import com.example.contactsrave.viewmodel.ContactViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AddEditContact : Fragment() {
    private val TAG = "AddEditContact"
    private lateinit var binding: FragmentAddEditContactBinding
    private val viewModel = ContactViewModel()
    private val arguments by navArgs<AddEditContactArgs>()
    private lateinit var contact: Contact

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddEditContactBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments.id

        setUpObservers()
        setUpListeners()

        viewModel.fetchContact(id, requireContext())

    }

    private fun setUpListeners() {
        binding.btnApply.setOnClickListener(View.OnClickListener {
            saveInformation()
        })
    }

    private fun setUpObservers() {
        viewModel.contacts.observe(viewLifecycleOwner,
            Observer<List<Contact>> {
                Log.i(TAG, "setUpObservers: First eMail -> ${it[0].eMail}")
            })

        viewModel.updateRtn.observe(viewLifecycleOwner,
            Observer<Long> {
                Log.i(TAG, "setUpObservers: return from viewModel -> $it")
            })

        viewModel.contact.observe(viewLifecycleOwner,
            Observer<Contact> {
                contact = it
                loadInfo()
                Log.i(TAG, "setUpObservers: return from get contact in viewModel -> ${it.id} ${it.first_name.toString()}")
            })
    }

    private fun saveInformation(){


        binding.let{
            contact.apply{
                eMail = it.etEmail.text.toString()
                first_name = it.etFirstName.text.toString()
                last_name = it.etLastName.text.toString()
            }
        }

        viewModel.updateContact(contact, requireContext())
    }

    private fun loadInfo() {
        contact.let{
            binding.apply {
                etEmail.setText(it.eMail)
                etFirstName.setText(it.first_name)
                etLastName.setText(it.last_name)
            }
        }
    }

}