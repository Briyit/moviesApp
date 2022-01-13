package com.example.movies

import com.example.movies.models.MoviesData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("4/list/8175093?page=1&api_key=462191cc3e3ec9071babaccd5f8c4c16")
    fun getMovies(): Call<MoviesData>

}