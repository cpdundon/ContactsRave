package com.example.contactsrave.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactsrave.databinding.FragmentContactBinding
import com.example.contactsrave.databinding.FragmentSelectContactBinding

class ContactFragment : Fragment() {
    private val TAG = "ContactFragment"
    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentContactBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root
}