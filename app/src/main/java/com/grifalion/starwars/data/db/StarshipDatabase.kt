package com.grifalion.starwars.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grifalion.starwars.data.db.dao.StarshipDao
import com.grifalion.starwars.data.db.entity.StarshipEntity

@Database(entities = [StarshipEntity::class], version = 1)
abstract class StarshipDatabase: RoomDatabase() {
    abstract fun getDao(): StarshipDao

    companion object{
        private var database: StarshipDatabase? = null

        fun getMainDatabase(context: Context): StarshipDatabase{
            return if(database==null){
                database = Room.databaseBuilder(
                    context.applicationContext,
                StarshipDatabase::class.java,
                "starshipDatabase"
                ).build()
                database as StarshipDatabase
            } else {
                database as StarshipDatabase
            }
        }
    }
}
