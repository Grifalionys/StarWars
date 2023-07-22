package com.grifalion.starwars.presentation.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.grifalion.starwars.data.db.entity.PeopleEntity
import com.grifalion.starwars.data.db.entity.StarshipEntity
import com.grifalion.starwars.data.network.dto.people.PeopleResultDto
import com.grifalion.starwars.data.network.dto.starship.StarshipResultDto
import com.grifalion.starwars.databinding.MainFragmentBinding
import com.grifalion.starwars.presentation.fragments.favourite.FavouriteViewModel
import com.grifalion.starwars.presentation.fragments.main.adapters.CharacterAdapter
import com.grifalion.starwars.presentation.fragments.main.adapters.StarShipAdapter

class MainFragment: Fragment(), CharacterAdapter.FavouriteListener, StarShipAdapter.StarshipListener {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFavourite: FavouriteViewModel
    private val characterAdapter = CharacterAdapter(this)
    private val starShipAdapter = StarShipAdapter(this)
    private var name = EMPTY_STRING

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModelFavourite = ViewModelProvider(requireActivity())[FavouriteViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvView.adapter = characterAdapter
        binding.rvStarships.adapter = starShipAdapter
        viewModelFavourite.initDatabase()
        viewModel.peopleList.observe(viewLifecycleOwner) { response ->
            if(response.isSuccessful){
                response.body()?.let { characterAdapter.setPeople(it.results) }
            } else {
                response.body()?.let { characterAdapter.setPeople(it.results) }
            }
        }
        viewModel.starShipList.observe(viewLifecycleOwner) { response ->
            if(response.isSuccessful){
                response.body()?.let { starShipAdapter.setStarship(it.results)}
            } else {
                response.body()?.let { starShipAdapter.setStarship(it.results)}
            }
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                name = query.toString()
                if(name.length > 1) {
                    viewModel.getCharacterByName(name)
                    viewModel.getStarshipByName(name)
                    binding.rvView.visibility = View.VISIBLE
                    binding.rvStarships.visibility = View.VISIBLE
                } else {
                    binding.rvView.visibility = View.GONE
                    binding.rvStarships.visibility = View.GONE
                }
                return true
            }
        })
    }
    companion object{
        private const val EMPTY_STRING = ""
    }

    override fun addItemFavourite(peopleResultDto: PeopleResultDto) {
        viewModelFavourite.insertPeople(
            PeopleEntity(
                name = peopleResultDto.name.toString(),
                gender = peopleResultDto.gender.toString(),
                starships = peopleResultDto.starships?.size.toString()
            )
        )
    }

    override fun addItemStarShip(starshipResultDto: StarshipResultDto) {
        viewModelFavourite.insertStarShip(
            StarshipEntity(
                name = starshipResultDto.name.toString(),
                model = starshipResultDto.model.toString(),
                manufacturer = starshipResultDto.manufacturer.toString(),
                passengers = starshipResultDto.passengers.toString()
            )
        )
    }
}
