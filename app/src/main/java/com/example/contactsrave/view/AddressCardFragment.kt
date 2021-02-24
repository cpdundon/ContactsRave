package com.example.contactsrave.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactsrave.databinding.FragmentAddressCardBinding
import com.example.contactsrave.databinding.FragmentContactBinding


class AddressCardFragment : Fragment() {
    private val TAG = "AddressCardFragment"
    private lateinit var binding: FragmentAddressCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddressCardBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root
}