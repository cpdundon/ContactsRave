package com.example.contactsrave.repo

import android.content.Context
import com.example.contactsrave.database.ContactDao
import com.example.contactsrave.database.ContactDatabase
import com.example.contactsrave.model.Contact

@ExperimentalStdlibApi
object ContactRepo {

    fun getContacts (context: Context) = ContactDatabase.getDatabase(context).contactDao().findContacts()
//    suspend fun getContacts(
//        context: Context,
//        ): List<Contact>? {
//            return ContactDatabase.getDatabase(context).contactDao().findContacts()
//    }

    suspend fun saveContact(contact: Contact, context: Context) : Long {
        val contactDao = ContactDatabase.getDatabase(context).contactDao()
        return contactDao.insert(contact)
    }

    fun getContact (id: Long, context: Context) = ContactDatabase.getDatabase(context).contactDao().findContact(id)
//    suspend fun getContact(
//        id: Long, context: Context,
//    ): Contact? {
//        return if (id == 0L) {
//            Contact(null, null, null, null, listOf())
//        } else {
//            ContactDatabase.getDatabase(context).contactDao().findContact(id)
//        }
//    }

}

