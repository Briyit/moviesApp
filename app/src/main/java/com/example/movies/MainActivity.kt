package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    //val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.root
    }
}