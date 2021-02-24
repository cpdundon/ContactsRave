package com.example.contactsrave.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.contactsrave.model.Contact

@ExperimentalStdlibApi
@Database(entities = [Contact::class], version = 1)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        private var INSTANCE: ContactDatabase? = null
        private const val DB_NAME = "Contact.db"

        fun getDatabase(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(ContactDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext, ContactDatabase::class.java, DB_NAME
                        ).build()

                    }
                }
            }

            return INSTANCE!!

        }
    }
}