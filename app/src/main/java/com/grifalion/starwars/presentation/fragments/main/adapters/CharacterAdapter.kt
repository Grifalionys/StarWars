package com.grifalion.starwars.presentation.fragments.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grifalion.starwars.data.network.dto.people.PeopleResultDto
import com.grifalion.starwars.databinding.ItemPeopleBinding

class CharacterAdapter(val listener: FavouriteListener): RecyclerView.Adapter<CharacterAdapter.MainViewHolder>() {
    private var listPeople = emptyList<PeopleResultDto>()

    class MainViewHolder(var binding: ItemPeopleBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemPeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = listPeople.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = listPeople[position]
        holder.binding.tvNamePerson.text = item.name.toString()
        holder.binding.tvGenderPerson.text = item.gender.toString()
        holder.binding.tvStarshipsPerson.text = item.starships?.size.toString()
        holder.binding.btnFavourite.visibility = View.VISIBLE
        holder.binding.btnFavourite.setOnClickListener {
            listener.addItemFavourite(item)
            holder.binding.btnFavourite.visibility = View.GONE
        }
    }

    fun setPeople(person: List<PeopleResultDto>){
        listPeople = person
        notifyDataSetChanged()
    }

    interface FavouriteListener{
        fun addItemFavourite(peopleResultDto: PeopleResultDto)
    }
}
