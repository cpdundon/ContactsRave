package com.example.contactsrave.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contactsrave.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact ORDER BY last_name, first_name")
    fun findContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact WHERE id = :id LIMIT 1")
    fun findContact(id: Long): Flow<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg contacts: Contact)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(contact: Contact)

    @Query("DELETE FROM Contact")
    suspend fun deleteAll()

//    @Query("SELECT * FROM WeatherOneCall ORDER BY id DESC")
//    suspend fun findAll(): LiveData<List<WeatherOneCall>>
}
