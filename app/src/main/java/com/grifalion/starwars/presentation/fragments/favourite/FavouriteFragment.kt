package com.grifalion.starwars.presentation.fragments.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.grifalion.starwars.data.db.entity.PeopleEntity
import com.grifalion.starwars.data.db.entity.StarshipEntity
import com.grifalion.starwars.databinding.FavouriteFragmentBinding
import com.grifalion.starwars.presentation.fragments.favourite.adapters.CharacterAdapter
import com.grifalion.starwars.presentation.fragments.favourite.adapters.StarShipAdapter

class FavouriteFragment: Fragment(), CharacterAdapter.FavouriteListener,StarShipAdapter.ListenerDeleteStarShip {
    private var _binding: FavouriteFragmentBinding? = null
    private val binding get() = _binding!!
    private val characterAdapter = CharacterAdapter(this)
    private val starshipAdapter = StarShipAdapter(this)
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavouriteFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[FavouriteViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPeople.adapter = characterAdapter
        binding.rvStarship.adapter = starshipAdapter
        viewModel.initDatabase()
        viewModel.getAllPeople().observe(viewLifecycleOwner) {
            characterAdapter.setPeople(it)
        }
        viewModel.getStarships().observe(viewLifecycleOwner){
            starshipAdapter.setStarShip(it)
        }
    }

    override fun deleteItem(peopleEntity: PeopleEntity) {
        viewModel.deletePeople(peopleEntity)
    }

    override fun deleteStarship(starshipEntity: StarshipEntity) {
        viewModel.deleteStarShip(starshipEntity)
    }
}