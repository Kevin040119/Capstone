package com.example.capstone.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvTrending : RecyclerView
    private lateinit var rvPopular : RecyclerView
    private lateinit var rvMyList : RecyclerView
    private lateinit var auth: FirebaseAuth

    private val homeViewModel : HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        //RecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager3 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTrending = findViewById<RecyclerView>(R.id.trendingList)
        rvPopular = findViewById(R.id.popularList)
        rvMyList = findViewById(R.id.myList)
        rvTrending.layoutManager = layoutManager
        rvPopular.layoutManager = layoutManager2
        rvMyList.layoutManager = layoutManager3

        //Set Trending
        homeViewModel.getTrend().observe(this) {item ->
            setTrendingList(item!!)
        }

        //Set popular
        homeViewModel.getPopular().observe(this) {item ->
            setPopular(item!!)
        }

        //Set Your List
        homeViewModel.myList.observe(this) {item ->
            setMyList(item!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.myList -> {
                val uri = Uri.parse("capstone://mylist")
                startActivity(Intent(Intent.ACTION_VIEW, uri))

            }
        }
        return true
    }

    private fun setTrendingList(movie : List<Movie>) {
        val adapter = HomeAdapter()
        adapter.submitList(movie)
        rvTrending.adapter = adapter
    }

    private fun setPopular(movie : List<Movie>){
        val adapter = HomeAdapter2()
        adapter.submitList(movie)
        rvPopular.adapter = adapter
    }

    private fun setMyList(movie : List<Movie>){
        val adapter = HomeAdapter2()
        adapter.submitList(movie)
        rvMyList.adapter = adapter
    }

}