package com.example.contactsmanagerapp.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contacts::class], version = 1 , exportSchema =false)
//here we are creating the database with entities as Contacts

abstract  class ContactDatabase :RoomDatabase(){
    abstract val ContactList: ContactList
    //here we need to mention  the ContactList DAO
    // We need to implement the singleton design pattern to avoid the overhead .
    companion object {
        @Volatile
// this allows it to automatically read and write , and prevents any possible race conditions in multithreading.
        private var INSTANCE: ContactDatabase? = null
        fun getInstance(context: Context): ContactDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    // creating the database objects
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contacts_db"
                    ).build()

                }
                INSTANCE = instance
                return instance

            }

        }
    }
    }



