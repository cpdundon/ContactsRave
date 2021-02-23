package com.example.contactsrave.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsrave.adapter.ContactsAdapter
import com.example.contactsrave.databinding.FragmentAddEditContactBinding
import com.example.contactsrave.databinding.FragmentSelectContactBinding
import com.example.contactsrave.model.Contact
import com.example.contactsrave.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.fragment_select_contact.*


class SelectContactFragment : Fragment() {
        private val TAG = "SelectContactFragment"
        private lateinit var binding: FragmentSelectContactBinding
        private val viewModel = ContactViewModel()
        private val default_id = 0L

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
            setUpObservers()
            binding.rvContacts.layoutManager = getGridLayoutMgr()
            viewModel.fetchContacts(requireContext())
        }

    private fun setUpListeners() {
        binding.btnFetch.setOnClickListener(View.OnClickListener {
            val action = SelectContactFragmentDirections.actionSelectContactFragmentToAddEditContact(default_id)
            findNavController().navigate(action)
        })
    }

    private fun setUpObservers() {
        viewModel.contacts.observe(viewLifecycleOwner,
            Observer<List<Contact>> {
                val contactsAdapter = ContactsAdapter(it)
                binding.rvContacts.adapter = contactsAdapter
            })
    }

    private fun getGridLayoutMgr(horizontal: Boolean = false, gridToggle: Boolean = false): RecyclerView.LayoutManager {
        val const = if (horizontal) {
            LinearLayoutManager.HORIZONTAL
        } else {
            LinearLayoutManager.VERTICAL
        }

        return if (gridToggle) {
            GridLayoutManager(this.context, 2)
        } else {
            LinearLayoutManager(this.context, const, false)
        }
    }
}