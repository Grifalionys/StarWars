package com.grifalion.starwars.presentation.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grifalion.starwars.data.network.dto.people.PeopleDto
import com.grifalion.starwars.data.network.dto.starship.StarshipDto
import com.grifalion.starwars.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {
    val repository = MainRepository()
    var peopleList: MutableLiveData<Response<PeopleDto>> = MutableLiveData()
    var starShipList: MutableLiveData<Response<StarshipDto>> = MutableLiveData()

    fun getCharacterByName(search: String){
        viewModelScope.launch(Dispatchers.IO){
            peopleList.postValue(repository.getCharacterByName(search))
        }
    }

    fun getStarshipByName(search: String){
        viewModelScope.launch(Dispatchers.IO){
            starShipList.postValue(repository.getStarshipByName(search))
        }
    }
}
