package com.grifalion.starwars.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grifalion.starwars.data.db.dao.PeopleDao
import com.grifalion.starwars.data.db.entity.PeopleEntity

@Database(entities = [PeopleEntity::class], version = 2)
abstract class PeopleDatabase: RoomDatabase() {
    abstract fun getPeopleDao(): PeopleDao

    companion object{
        var database: PeopleDatabase? = null

        fun getMainDatabase(context: Context): PeopleDatabase{
            return if(database==null){
                database = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleDatabase::class.java,
                    "peopleDatabase"
                ).build()
                database as PeopleDatabase
            } else {
                database as PeopleDatabase
            }
        }
    }
}