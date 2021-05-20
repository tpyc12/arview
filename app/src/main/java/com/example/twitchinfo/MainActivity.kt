package com.example.twitchinfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.twitchinfo.ViewModel.MovieViewModel
import com.example.twitchinfo.adapters.MovieAdapter
import com.example.twitchinfo.databinding.ActivityGamesListBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel

    private lateinit var ui: ActivityGamesListBinding

    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)
        ui = ActivityGamesListBinding.inflate(layoutInflater)
        setContentView(ui.root)

        val adapter = MovieAdapter()
        ui.rvGamesList.layoutManager = GridLayoutManager(this, 2)
        ui.rvGamesList.adapter = adapter

        adapter.onPosterClickListener = object : MovieAdapter.OnPosterClickListener {
            override fun onPosterClick(position: Int) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.movieList.observe(this, {
            adapter.movieInfoList = it
        })

        adapter.onReachEndListener = object : MovieAdapter.OnReachEndListener {
            override fun onReachEnd() {
                page++
                viewModel.loadData(page)
            }
        }
    }
}