package com.example.capstone.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getTrending() = movieRepository.getTrending()
    override fun getPopular() = movieRepository.getPopular()
    override fun insert(myList: Movie) = movieRepository.insert(myList)
    override fun delete(myList: Movie) = movieRepository.delete(myList)
    override fun getMyList(movieId: Int): LiveData<Movie> = movieRepository.getMyList(movieId)
    override fun getAllMyList() : LiveData<List<Movie>> = movieRepository.getAllMyList()
}