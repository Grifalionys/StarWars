package com.grifalion.starwars.presentation.fragments.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.grifalion.starwars.data.db.entity.StarshipEntity
import com.grifalion.starwars.data.network.dto.starship.StarshipResultDto
import com.grifalion.starwars.databinding.ItemStarshipBinding

class StarShipAdapter(val starshipListener: StarshipListener): RecyclerView.Adapter<StarShipAdapter.StarshipViewHolder>() {
    private var listStarships = emptyList<StarshipResultDto>()

    class StarshipViewHolder(var binding: ItemStarshipBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipViewHolder {
       return StarshipViewHolder(ItemStarshipBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int  = listStarships.size

    override fun onBindViewHolder(holder: StarshipViewHolder, position: Int) {
        val item = listStarships[position]
        holder.binding.tvName.text = item.name
        holder.binding.tvManufacturer.text = item.manufacturer
        holder.binding.tvModel.text = item.model
        holder.binding.tvPassengers.text = item.passengers
        holder.binding.btnAddFavourite.visibility = View.VISIBLE
        holder.binding.btnAddFavourite.setOnClickListener {
            starshipListener.addItemStarShip(item)
        }
    }

    fun setStarship(starships: List<StarshipResultDto>){
        listStarships = starships
        notifyDataSetChanged()
    }
    interface StarshipListener{
        fun addItemStarShip(starshipResultDto: StarshipResultDto)
    }
}
