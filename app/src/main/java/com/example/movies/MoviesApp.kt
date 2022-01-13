package com.example.movies

import android.app.Application
import android.content.Context

class MoviesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
    }
}