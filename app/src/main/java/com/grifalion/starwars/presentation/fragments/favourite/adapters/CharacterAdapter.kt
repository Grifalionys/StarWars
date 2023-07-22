package com.grifalion.starwars.presentation.fragments.favourite.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grifalion.starwars.data.db.entity.PeopleEntity
import com.grifalion.starwars.databinding.ItemPeopleBinding

class CharacterAdapter(var favouriteListener: FavouriteListener): RecyclerView.Adapter<CharacterAdapter.FavouriteViewHolder>() {
    private var listPeople = emptyList<PeopleEntity>()

    class FavouriteViewHolder(var binding: ItemPeopleBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(ItemPeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int  = listPeople.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val item = listPeople[position]
        holder.binding.tvNamePerson.text = item.name
        holder.binding.tvGenderPerson.text = item.gender
        holder.binding.tvStarshipsPerson.text = item.starships
        holder.binding.btnDelete.visibility = View.VISIBLE
        holder.binding.btnDelete.setOnClickListener {
            favouriteListener.deleteItem(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPeople(persons: List<PeopleEntity>){
        listPeople = persons
        notifyDataSetChanged()
    }

    interface FavouriteListener{
        fun deleteItem(peopleEntity: PeopleEntity)
    }
}
