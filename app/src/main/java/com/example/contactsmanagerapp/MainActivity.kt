package com.example.contactsmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmanagerapp.viewModel.ContactViewModel
import com.example.contactsmanagerapp.viewModel.ViewModelFactory
import com.example.contactsmanagerapp.databinding.ActivityMainBinding
import com.example.contactsmanagerapp.repository.room.ContactDatabase
import com.example.contactsmanagerapp.repository.room.Contacts
import com.example.contactsmanagerapp.repositorylayer.ContactRepository
import com.example.contactsmanagerapp.view.CustomAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel:ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        // Room database
        val dao = ContactDatabase.getInstance(applicationContext).ContactList
        val repository = ContactRepository(dao)
        val factory = ViewModelFactory(repository)
         contactViewModel=ViewModelProvider(this,factory).get(ContactViewModel::class.java)
        //view model
       // contactViewModel=ViewModelProvider(this).get(ContactViewModel::class.java)
        binding.user=contactViewModel
        // Livedata is an observable data holder class, tying it to a lifecycle owner ,like an activity or fragment ,ensures that it only updates when the associated lifecycle is in an active state .
        // this helps prevent memory leaks ,and this is the context of android.
        //When you see this inside an activity or fragment ,it refers to the instance of that activity or fragment .
        // Live data and data binding integration.
        binding .lifecycleOwner=this
        initrecyclerview()
    }
    private fun initrecyclerview(){
        binding.rview.layoutManager=LinearLayoutManager(this)
        DisplayusersList()
    }

    private fun DisplayusersList() {
        contactViewModel.users.observe(this, Observer { binding.rview.adapter=CustomAdapter(
            it,{selecteditem:Contacts->listItemClicked(selecteditem)}
            // here the lambda expression takes the single parameter that is the selecteditem and it is of type contacts and calls the l
        // istitemclicked function with that parameter seleccteditem.
        ) })
    }

    private fun listItemClicked(selecteditem: Contacts) {
        Toast.makeText(this, "selected name is ${selecteditem.name} " , Toast.LENGTH_SHORT).show()
        contactViewModel.initUpdateAndDelete(selecteditem)

    }


}