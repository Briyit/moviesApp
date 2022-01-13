package com.example.movies.viewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.ResourceRetrofit
import com.example.movies.models.MoviesData
import com.example.movies.models.ResultData
import com.example.movies.persistence.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainViewModel: ViewModel() {

    private val moviesRepository: MoviesRepository by lazy {
        MoviesRepository()
    }

    val moviesResponse: MutableLiveData<MoviesData> by lazy {
        MutableLiveData<MoviesData>()
    }

    val moviesSaved: MutableLiveData<MoviesData>? by lazy {
        MutableLiveData<MoviesData>()
    }

    fun saveMovies(): LiveData<MoviesData>? {
        return moviesResponse
    }
    fun insertMovies(movies:MoviesData) {
        moviesRepository.insertMovies(movies)
    }

    fun getSavedMovies(): LiveData<MoviesData>? {
        moviesSaved?.value = moviesRepository.getMovies()
        return moviesSaved
    }

    fun getListMostPopular(): List<ResultData>? {
        return moviesRepository.getMovies()?.moviesResult?.sortedByDescending { it.popularity }?.toMutableList() as ArrayList<ResultData>
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListPlayingNow(): List<ResultData>? {
        val movies = moviesRepository.getMovies()?.moviesResult
        return movies?.sortedByDescending { LocalDate.parse(it.releaseDate, DateTimeFormatter.ISO_DATE) }
    }

    fun getMovies() {
        ResourceRetrofit.resources.getMovies()
            .enqueue(object : Callback<MoviesData> {
                override fun onResponse(call: Call<MoviesData>, response: Response<MoviesData>) {
                    moviesResponse.value = response.body()
                }

                override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                    Log.d("errorfatal", t.message.toString())
                }

            })
    }
}