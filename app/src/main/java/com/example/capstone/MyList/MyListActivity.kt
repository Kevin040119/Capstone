package com.example.capstone.MyList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.Home.HomeAdapter2
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.ui.ViewModelFactory
import com.example.capstone.databinding.ActivityMyListBinding

class MyListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyListBinding
    private lateinit var myListViewModel: MyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "My List"

        val layoutManager = GridLayoutManager(this,3)
        binding.rvMyList.layoutManager = layoutManager

        //setView
        val factory = ViewModelFactory.getInstance(this)
        myListViewModel = ViewModelProvider(this, factory)[MyListViewModel::class.java]

        myListViewModel.getAllMyList().observe(this) {
            setMyList(it)
        }
    }

    private fun setMyList(data : List<Movie>){
        val adapter = HomeAdapter2()
        adapter.submitList(data)
        binding.rvMyList.adapter = adapter
    }
}