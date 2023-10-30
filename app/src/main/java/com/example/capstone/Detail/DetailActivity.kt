package com.example.capstone.Detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.ui.ViewModelFactory
import com.example.capstone.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel : DetailViewModel
    private lateinit var movie : Movie
    private var indicator : Boolean = false

    companion object{
        const val MOVIE = "movie"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra(MOVIE)!!

        setupDetailView()

        binding.fab.setOnClickListener(this)
    }

    private fun setupDetailView() {
        Glide.with(binding.root)
            .load("https://image.tmdb.org/t/p/original/"+movie.movieBackdrop)
            .into(binding.imgDetail)

        binding.Language.text = movie.movieLanguage
        binding.tvTitle.text = movie.movieTitle
        binding.tvDescription.text = movie.movieDescription
        binding.tvVote.text = movie.movieVote
        binding.tvVoteCount.text = "(${movie.movieVoteCount})"
        binding.tvYear.text = movie.movieRelease

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        detailViewModel.getMyList(movie.movieId!!).observe(this) {
            if(it != null) {
                binding.fab.setImageResource(R.drawable.baseline_check_24)
                indicator = true
            }else {
                binding.fab.setImageResource(R.drawable.baseline_add_24)
                indicator = false
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab -> {
                if(!indicator) {
                    detailViewModel.insert(movie)
                    indicator = true
                }
            }
        }
    }
}