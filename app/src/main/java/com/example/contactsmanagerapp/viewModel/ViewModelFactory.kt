package com.example.contactsmanagerapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactsmanagerapp.repositorylayer.ContactRepository

// view model factory :pass required parameters to the view model
class ViewModelFactory (private val repo: ContactRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
            return ContactViewModel(repo) as  T
            // here we are creating a contact viewmodel in  create fun
        }
        throw IllegalArgumentException("Unknown view miodel class")


    }

}