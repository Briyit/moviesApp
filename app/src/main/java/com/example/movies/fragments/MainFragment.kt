package com.example.movies.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.MoviesAdapter
import com.example.movies.R
import com.example.movies.databinding.FragmentMainBinding
import com.example.movies.models.ResultData
import com.example.movies.viewModels.MainViewModel

class MainFragment: Fragment() {

    val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentMainBinding
    lateinit var adapter: MoviesAdapter
    lateinit var rvMovies: RecyclerView
    lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = binding.spinner
        rvMovies = binding.rvMovies
        rvMovies.layoutManager = LinearLayoutManager(requireContext())
        mainViewModel.getMovies()

        ArrayAdapter.createFromResource(requireContext(), R.array.options_array, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

        mainViewModel.saveMovies()?.observe(requireActivity(), Observer {
            if (it != null) {
                mainViewModel.insertMovies(it)
            }
        })

        mainViewModel.getSavedMovies()?.observe(requireActivity(), Observer {
            if (it != null) {
                adapter = MoviesAdapter(it.moviesResult!!,requireContext())
                rvMovies.adapter = MoviesAdapter(it.moviesResult!!, requireContext())
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        if (p2 == 0) {
                            val list = mainViewModel.getSavedMovies()
                            mainViewModel.getListMostPopular()
                            if (list != null) {
                                adapter = MoviesAdapter(mainViewModel.getListMostPopular()!!.toMutableList() as ArrayList<ResultData>,requireContext())
                                rvMovies.adapter = MoviesAdapter(mainViewModel.getListMostPopular()!!.toMutableList() as ArrayList<ResultData>, requireContext())
                            }
                        } else if (p2 == 1) {
                            val list =mainViewModel.getSavedMovies()
                            mainViewModel.getListPlayingNow()
                            if (list != null) {
                                adapter = MoviesAdapter(mainViewModel.getListPlayingNow()!!.toMutableList() as ArrayList<ResultData>,requireContext())
                                rvMovies.adapter = MoviesAdapter(mainViewModel.getListPlayingNow()!!.toMutableList() as ArrayList<ResultData>, requireContext())
                            }
                        }

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                }
            }
        })
    }
}