package com.grifalion.starwars.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starship_entity")
data class StarshipEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Model")
    val model: String,
    @ColumnInfo(name = "Manufacturer")
    val manufacturer: String,
    @ColumnInfo(name = "Passengers")
    val passengers: String,
)
