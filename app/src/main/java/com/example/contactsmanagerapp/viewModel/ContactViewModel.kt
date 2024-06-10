package com.example.contactsmanagerapp.viewModel

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmanagerapp.repository.room.Contacts
import com.example.contactsmanagerapp.repositorylayer.ContactRepository
import kotlinx.coroutines.launch

public class ContactViewModel(private val repo: ContactRepository):ViewModel() , Observable {
    //view models are used to store and manage UI related data in a lifecycle conscious way.
    val users = repo.contacts
    private var isupdateordelete=false
    private lateinit var contactToUpdateOrDelete:Contacts
    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val gmail = MutableLiveData<String?>()
    @Bindable
    val  saveOrUpdateButtonText=MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText=MutableLiveData<String>()

    init{
        //intialiser block
       saveOrUpdateButtonText.value ="save"
       clearAllOrDeleteButtonText.value="clearall"
    }
    //Introduction to coroutines

    fun insert(contacts: Contacts)= viewModelScope.launch {
        repo.insert(contacts)
    }
    fun delete(contacts: Contacts)=viewModelScope.launch { repo.delete(contacts)
          inputName.value=null
          gmail.value=null
        isupdateordelete=false
        saveOrUpdateButtonText.value="save"
        clearAllOrDeleteButtonText.value="clearall"


    }
    fun update(contacts: Contacts)= viewModelScope.launch {
        repo.update(contacts)
        //resetting the buttons and fields.
        inputName.value=null
        gmail.value=null
        isupdateordelete=false
        saveOrUpdateButtonText.value="save"
        clearAllOrDeleteButtonText.value="clearall"
    }
    fun clearAll()=viewModelScope.launch { repo.deleteAll() }
    fun saveOrUpdate()
    {
        if(isupdateordelete ){
            // make an update
            contactToUpdateOrDelete.name=inputName.value!!
            contactToUpdateOrDelete.email=gmail.value!!
            update(contactToUpdateOrDelete)

        }
        else{
            val name = inputName.value!!
            val email = gmail.value!!
            insert(Contacts(0,name, email))
            // reset the name and email
            inputName.value=null
            gmail.value=null

        }
    }
    fun clearAllorDelete(){
        if(isupdateordelete)
        {
            delete(contactToUpdateOrDelete)
        }else
        {
            clearAll()
        }
    }
    fun initUpdateAndDelete(contacts: Contacts)
    {
        inputName.value= contacts.name //mutable live data we need to use value
        // here we are working with
        gmail.value=contacts.email
        isupdateordelete=true
        contactToUpdateOrDelete=contacts
        saveOrUpdateButtonText.value="update"
        clearAllOrDeleteButtonText.value="delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        
    }


}