    package com.example.contactsrave.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactsrave.R
import com.example.contactsrave.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity() {
        lateinit var binding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            //setObservers()
        }
}