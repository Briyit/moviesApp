package com.example.movies.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM MoviesData")
    fun getMovies(): MoviesData?

    @Insert(onConflict = REPLACE)
    fun insertMovies(movies: MoviesData)
}