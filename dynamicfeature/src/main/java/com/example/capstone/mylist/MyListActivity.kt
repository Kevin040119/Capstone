package com.example.capstone.mylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.mylist.databinding.ActivityMyListBinding
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules


class MyListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyListBinding
    private val myListViewModel: MyListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(myListModule)

        supportActionBar?.title = "My List"

        val layoutManager = GridLayoutManager(this,3)
        binding.rvMyList.layoutManager = layoutManager

        myListViewModel.getAllMyList().observe(this) {
            setMyList(it)
        }
    }

    private fun setMyList(data : List<Movie>){
        val adapter = MyListAdapter()
        adapter.submitList(data)
        binding.rvMyList.adapter = adapter
    }
}