package com.example.contactsmanagerapp.repositorylayer

import com.example.contactsmanagerapp.repository.room.ContactList
import com.example.contactsmanagerapp.repository.room.Contacts
// whenever we use the repository we have to call all the methods in the DAO .
class ContactRepository(private val ContactList:ContactList) {
    val contacts =  ContactList.getAllContactsInDB()
    suspend fun insert(contact:Contacts):Long
    {
        return ContactList.insertContact(contact)
    }
    suspend fun  delete(contact: Contacts)
    {
        return ContactList.deletecontact(contact)
    }
    suspend fun update(contact: Contacts)
    {
        return ContactList.updateContact(contact)
    }
    suspend fun deleteAll(){
        return ContactList.deleteAll()
    }



}