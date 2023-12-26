package com.example.capstone.core.domain.usecase

import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.repository.IMovieRepository
import io.reactivex.Flowable

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getTrending() = movieRepository.getTrending()
    override fun getPopular() = movieRepository.getPopular()
    override fun insert(myList: Movie) = movieRepository.insert(myList)
    override fun delete(myList: Movie) = movieRepository.delete(myList)
    override fun getMyList(movieId: Int): Flowable<Movie> = movieRepository.getMyList(movieId)
    override fun getAllMyList() : Flowable<List<Movie>> = movieRepository.getAllMyList()
}