package com.grifalion.starwars.data.repository

import com.grifalion.starwars.data.db.dao.PeopleDao
import com.grifalion.starwars.data.db.dao.StarshipDao
import com.grifalion.starwars.data.db.entity.PeopleEntity
import com.grifalion.starwars.data.db.entity.StarshipEntity

class FavouriteRepository(val peopleDao: PeopleDao, val starshipDao: StarshipDao) {

    suspend fun insertPeople(peopleEntity: PeopleEntity) = peopleDao.insert(peopleEntity)

    suspend fun deletePeople(peopleEntity: PeopleEntity) = peopleDao.delete(peopleEntity)

    fun getAllPeople() = peopleDao.getAllPeople()

    suspend fun insertStarship(starshipEntity: StarshipEntity) = starshipDao.insert(starshipEntity)

    suspend fun deleteStarship(starshipEntity: StarshipEntity) = starshipDao.delete(starshipEntity)

    fun getStarships() = starshipDao.getStarships()
}
