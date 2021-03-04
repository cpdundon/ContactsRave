package com.example.contactsrave.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.contactsrave.model.Contact
import com.example.contactsrave.repo.ContactRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalStdlibApi
class ContactViewModel : ViewModel() {
    companion object {
        private const val TAG = "ContactsViewModel"
    }

    private val _contacts = MutableLiveData<List<Contact>>()
    private val _contact = MutableLiveData<Contact>()
    private val _updateRtn = MutableLiveData<Long>()

    fun getContacts (context: Context) = ContactRepo.getContacts(context).asLiveData(viewModelScope.coroutineContext)
    fun getContact (id: Long, context: Context) = ContactRepo.getContact(id, context).asLiveData(viewModelScope.coroutineContext)
//    val contacts: LiveData<List<Contact>>
//        get() = _contacts

    val contact: LiveData<Contact>
        get() = _contact

    val updateRtn: LiveData<Long>
        get() = _updateRtn

//    fun fetchContacts(context: Context)
//    {
//        viewModelScope.launch(Dispatchers.IO) {
//            ContactRepo.getContacts(context).let{
//                _contacts.postValue(it)
//            }
//        }
//    }

//    fun fetchContact(id: Long, context: Context)
//    {
//        viewModelScope.launch(Dispatchers.IO) {
//            ContactRepo.getContact(id, context).let{
//                _contact.postValue(it)
//            }
//        }
//    }

    fun updateContact(contact: Contact, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val rtn = ContactRepo.saveContact(contact, context)
            Log.i(TAG, "updateContact: Return from DB -> $rtn")
            _updateRtn.postValue(rtn)

            if (rtn > 0L) {
                getContacts(context)
            }
        }
    }

}