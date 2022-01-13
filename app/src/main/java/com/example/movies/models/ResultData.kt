package com.example.movies.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultData(

    @SerializedName("adult")
    var adult:Boolean? = null,

    @SerializedName("backdrop_path")
    var path: String? = null,

    @SerializedName("genre_ids")
    var genreId: ArrayList<Int>? = null,

    @SerializedName("id")
    var idMovie: Int? = null,

    @SerializedName("media_type")
    var mediaType: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    var overview: String? = null,

    var popularity: Float? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    var title: String? = null,

    var video: Boolean? = null,

    @SerializedName("vote_average")
    var voteAverage: Float? = null,

    @SerializedName("vote_count")
    var voteCount: Int? = null
): Parcelable
