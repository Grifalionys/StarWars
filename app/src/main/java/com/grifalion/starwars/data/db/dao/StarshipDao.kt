package com.grifalion.starwars.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.grifalion.starwars.data.db.entity.PeopleEntity
import com.grifalion.starwars.data.db.entity.StarshipEntity

@Dao
interface StarshipDao {

    @Insert
    suspend fun insert(starshipEntity: StarshipEntity)

    @Delete
    suspend fun delete(starshipEntity: StarshipEntity)

    @Query("SELECT * FROM starship_entity")
    fun getStarships(): LiveData<List<StarshipEntity>>

}