package com.grifalion.starwars.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grifalion.starwars.data.db.entity.PeopleEntity

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(peopleEntity: PeopleEntity)

    @Delete
    suspend fun delete(peopleEntity: PeopleEntity)

    @Query("SELECT * FROM people_entity")
    fun getAllPeople(): LiveData<List<PeopleEntity>>
}