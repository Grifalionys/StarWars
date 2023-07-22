package com.grifalion.starwars.data.repository

import com.grifalion.starwars.data.network.ApiService
import com.grifalion.starwars.data.network.dto.people.PeopleDto
import com.grifalion.starwars.data.network.dto.starship.StarshipDto
import retrofit2.Response

class MainRepository() {

    suspend fun getCharacterByName(search: String): Response<PeopleDto>{
        return ApiService.getInstance().getCharacterByName(search)
    }

    suspend fun getStarshipByName(search: String): Response<StarshipDto>{
        return ApiService.getInstance().getStarshipByName(search)
    }
}
