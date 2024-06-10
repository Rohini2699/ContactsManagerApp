package com.example.contactsmanagerapp.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts_table")
data class Contacts(

    @PrimaryKey (autoGenerate = true)
   // this above line will assign the  unique values to the primary key .
    val id :Int,
    var name:String,
    var email:String

)

