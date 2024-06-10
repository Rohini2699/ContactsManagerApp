package com.example.contactsmanagerapp.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface ContactList {
    @Insert
    suspend fun insertContact(contacts: Contacts):Long
    @Update
    suspend fun updateContact(contacts: Contacts)
    @Delete
    suspend fun deletecontact(contacts: Contacts)
    @Query("DELETE FROM contacts_table")
    suspend fun deleteAll()
    @Query("SELECT *FROM contacts_table")
    fun getAllContactsInDB():LiveData<List<Contacts>>

}
