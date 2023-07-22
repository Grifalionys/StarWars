package com.grifalion.starwars.presentation.fragments.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grifalion.starwars.data.db.PeopleDatabase
import com.grifalion.starwars.data.db.StarshipDatabase
import com.grifalion.starwars.data.db.dao.StarshipDao
import com.grifalion.starwars.data.db.entity.PeopleEntity
import com.grifalion.starwars.data.db.entity.StarshipEntity
import com.grifalion.starwars.data.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var repository: FavouriteRepository
    private val context = application

    fun initDatabase(){
        val peopleDao = PeopleDatabase.getMainDatabase(context).getPeopleDao()
        val starShipDao = StarshipDatabase.getMainDatabase(context).getDao()
        repository = FavouriteRepository(peopleDao,starShipDao)
    }


    fun insertPeople(peopleEntity: PeopleEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertPeople(peopleEntity)
        }
    }

    fun insertStarShip(starShipEntity: StarshipEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertStarship(starShipEntity)
        }
    }

    fun deletePeople(peopleEntity: PeopleEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePeople(peopleEntity)
        }
    }

    fun deleteStarShip(starShipEntity: StarshipEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteStarship(starShipEntity)
        }
    }

    fun getAllPeople() = repository.getAllPeople()

    fun getStarships() = repository.getStarships()

}
