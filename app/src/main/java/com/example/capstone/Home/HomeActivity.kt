package com.example.capstone.Home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.MainActivity
import com.example.capstone.MyList.MyListActivity
import com.example.capstone.R
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.ui.ViewModelFactory
import com.example.capstone.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel : HomeViewModel
    private lateinit var rvTrending : RecyclerView
    private lateinit var rvPopular : RecyclerView
    private lateinit var rvMyList : RecyclerView
    private lateinit var auth: FirebaseAuth

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

        //SetTrend
        val factory = ViewModelFactory.getInstance(this)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        //Set Trending
        homeViewModel.trend.observe(this) {item ->
            setTrendingList(item!!)
        }

        //Set popular
        homeViewModel.popular.observe(this) { item ->
            setPopular(item!!)
        }

        //Set Your List
        homeViewModel.myList.observe(this) {item ->
            setMyList(item!!)
        }

        //Logout mechanism
        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.myList -> {
                startActivity(Intent(this@HomeActivity, MyListActivity::class.java))
            }

            R.id.logout -> {
                signOut()
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

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}