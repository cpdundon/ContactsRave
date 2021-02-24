package com.example.contactsrave.database

import androidx.room.TypeConverter
import com.example.contactsrave.model.Address
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter


@ExperimentalStdlibApi
class Converters {

    @TypeConverter
    fun addressesToString(addresses: List<Address>): String {
        val adapter = Moshi.Builder().build().adapter<List<Address>>()
        return adapter.toJson(addresses)
    }

    @TypeConverter
    fun stringToAddresses(jsonString: String): List<Address> {
        val type = Types.newParameterizedType(List::class.java, Address::class.java)
        val adapter = Moshi.Builder().build().adapter<List<Address>>(type)
        return adapter.fromJson(jsonString) ?: listOf()
    }
}