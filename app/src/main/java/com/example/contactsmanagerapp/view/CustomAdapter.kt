package com.example.contactsmanagerapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanagerapp.R
import com.example.contactsmanagerapp.databinding.CardItemBinding
import com.example.contactsmanagerapp.repository.room.Contacts

class CustomAdapter (private val contactsList:List<Contacts>,private val clickListner:(Contacts)->Unit):RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (contact:Contacts ,clickListner: (Contacts) -> Unit)
        {
            binding.nameTextview.text = contact.name
            binding.emailTextview.text=contact.email
            binding.linearLayout.setOnClickListener {
                clickListner(contact)
            }

        }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:CardItemBinding=DataBindingUtil.inflate(layoutInflater , R.layout.card_item , parent , false)
              return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  contactsList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactsList[position] ,clickListner)

    }
}