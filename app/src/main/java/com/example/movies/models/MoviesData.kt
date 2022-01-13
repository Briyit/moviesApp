package com.example.movies.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MoviesData(
    @PrimaryKey(autoGenerate = true) var roomId: Long? = null,
    @SerializedName("results")
    var moviesResult: ArrayList<ResultData>? = arrayListOf(),

    @SerializedName("sort_by")
    var sortBy: String? = null
)
