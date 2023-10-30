package com.example.capstone.MyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.usecase.MovieUseCase

class MyListViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getAllMyList() : LiveData<List<Movie>> = movieUseCase.getAllMyList()
}