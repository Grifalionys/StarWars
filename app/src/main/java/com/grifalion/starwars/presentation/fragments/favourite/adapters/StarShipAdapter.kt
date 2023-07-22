package com.grifalion.starwars.presentation.fragments.favourite.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grifalion.starwars.data.db.entity.StarshipEntity
import com.grifalion.starwars.databinding.ItemStarshipBinding

class StarShipAdapter(val listenerDeleteStarShip: ListenerDeleteStarShip): RecyclerView.Adapter<StarShipAdapter.StarShipViewHolder>() {
    private var listStarShips = emptyList<StarshipEntity>()

    class StarShipViewHolder(var binding: ItemStarshipBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarShipViewHolder {
        return StarShipViewHolder(
            ItemStarshipBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int  = listStarShips.size

    override fun onBindViewHolder(holder: StarShipViewHolder, position: Int){
        val item = listStarShips[position]
        holder.binding.tvName.text = item.name
        holder.binding.tvModel.text = item.model
        holder.binding.tvManufacturer.text = item.manufacturer
        holder.binding.tvPassengers.text = item.passengers
        holder.binding.btnDeleteStarship.visibility = View.VISIBLE
        holder.binding.btnDeleteStarship.setOnClickListener {
            listenerDeleteStarShip.deleteStarship(item)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setStarShip(starShips: List<StarshipEntity>){
        listStarShips = starShips
        notifyDataSetChanged()
    }
    interface ListenerDeleteStarShip{
        fun deleteStarship(starshipEntity: StarshipEntity)
    }
}