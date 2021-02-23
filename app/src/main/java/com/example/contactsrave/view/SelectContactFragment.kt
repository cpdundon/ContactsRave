package com.example.contactsrave.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.contactsrave.databinding.FragmentAddEditContactBinding
import com.example.contactsrave.databinding.FragmentSelectContactBinding
import com.example.contactsrave.viewmodel.ContactViewModel


class SelectContactFragment : Fragment() {
        private val TAG = "SelectContactFragment"
        private lateinit var binding: FragmentSelectContactBinding
        private val viewModel = ContactViewModel()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ) = FragmentSelectContactBinding.inflate(
            inflater,
            container,
            false
        ).also { binding = it }.root

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


            setUpListeners()

        }

    private fun setUpListeners() {
        binding.btnFetch.setOnClickListener(View.OnClickListener {
            val id = binding.etRecordId.text.toString().toLong()
            val action = SelectContactFragmentDirections.actionSelectContactFragmentToAddEditContact(id)
            findNavController().navigate(action)
        })
    }

}