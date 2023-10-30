package com.example.capstone.Detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun insert(myList: Movie) = movieUseCase.insert(myList)

    fun getMyList(movieId : Int) : LiveData<Movie> = movieUseCase.getMyList(movieId)
}