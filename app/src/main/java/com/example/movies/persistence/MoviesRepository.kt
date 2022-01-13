package com.example.movies.persistence

import android.content.Context
import com.example.movies.models.MoviesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MoviesRepository {

    companion object {
        private val moviesDao by lazy {
            MoviesDatabase.getDatabase().movieDao()
        }
    }

    fun insertMovies(movies: MoviesData) {
        runBlocking(Dispatchers.IO) {
            moviesDao.insertMovies(movies)
        }
    }

    fun getMovies(): MoviesData? {
        return runBlocking(Dispatchers.IO) {
            moviesDao.getMovies()
        }
    }
}