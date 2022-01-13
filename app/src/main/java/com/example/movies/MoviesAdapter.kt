package com.example.movies

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.models.ResultData
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.text.Html
import com.bumptech.glide.Glide
import java.io.File


class MoviesAdapter(var moviesList: ArrayList<ResultData>, val context: Context): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(holder.itemView).load("https://www.themoviedb.org/t/p/w355_and_h200_bestv2" + moviesList[position].posterPath)
            .centerCrop().into(holder.ivMovie)

        holder.tvTitle.text = Html.fromHtml(MoviesApp.context.getString(R.string.title_movie, moviesList[position].originalTitle))
        holder.tvOverview.text = Html.fromHtml(MoviesApp.context.getString(R.string.overview_movie, moviesList[position].overview))
        holder.tvPopularity.text = Html.fromHtml(MoviesApp.context.getString(R.string.popularity_movie, moviesList[position].popularity.toString()))
        holder.tvReleaseDate.text = Html.fromHtml(MoviesApp.context.getString(R.string.release_date, moviesList[position].releaseDate))
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }



}
class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val ivMovie = view.findViewById<ImageView>(R.id.ivMovie)
    val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    val tvOverview = view.findViewById<TextView>(R.id.tvOverview)
    val tvPopularity = view.findViewById<TextView>(R.id.tvPopularity)
    val tvReleaseDate = view.findViewById<TextView>(R.id.tvReleaseDate)

}