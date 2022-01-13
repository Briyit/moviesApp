package com.example.movies

import androidx.room.TypeConverter
import com.example.movies.models.ResultData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {
    @TypeConverter
    @JvmStatic
    fun toMoviesList(moviesList: String?): ArrayList<ResultData>? {
        val listType = object : TypeToken<ArrayList<ResultData>>() {}.type
        return if (moviesList == null) null else Gson().fromJson(moviesList, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromMoviesList(item: ArrayList<ResultData>?): String? {
        return if(item == null) null else Gson().toJson(item)
    }
}