package com.example.movies.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.MoviesApp
import com.example.movies.TypeConverter
import com.example.movies.models.MovieDao
import com.example.movies.models.MoviesData

@Database(entities = arrayOf(MoviesData::class), version = 1)
@TypeConverters(TypeConverter::class)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        fun getDatabase(): MoviesDatabase {
            val instance = Room.databaseBuilder(MoviesApp.context, MoviesDatabase::class.java, "movies_database").build()
            return instance
        }
    }
}