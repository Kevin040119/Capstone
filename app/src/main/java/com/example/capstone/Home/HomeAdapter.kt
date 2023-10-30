package com.example.capstone.Home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.Detail.DetailActivity
import com.example.capstone.core.data.remote.response.ResultsItem
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.databinding.ListTrendingBinding

class HomeAdapter : ListAdapter<Movie, HomeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding : ListTrendingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : Movie) {
            //https://image.tmdb.org/t/p/original/
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/original/"+movie.moviePoster)
                .into(binding.imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE, movie)
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}