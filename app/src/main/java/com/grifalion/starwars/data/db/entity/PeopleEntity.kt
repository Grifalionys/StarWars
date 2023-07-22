package com.grifalion.starwars.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people_entity")
data class PeopleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Gender")
    val gender: String,
    @ColumnInfo(name = "Starships")
    val starships: String
)
