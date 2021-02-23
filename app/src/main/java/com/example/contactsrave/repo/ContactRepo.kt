package com.example.contactsrave.repo

import android.content.Context
import com.example.contactsrave.database.ContactDatabase
import com.example.contactsrave.model.Contact

object ContactRepo {
    suspend fun getContacts(
        context: Context,
        ): List<Contact>? {
            return ContactDatabase.getDatabase(context).contactDao().findContacts()
    }

    suspend fun saveContact(contact: Contact, context: Context) : Long {
        val contactDao = ContactDatabase.getDatabase(context).contactDao()
        return contactDao.insert(contact)
    }

    suspend fun getContact(
        id: Long, context: Context,
    ): Contact? {
        return ContactDatabase.getDatabase(context).contactDao().findContact(id)
    }

}

