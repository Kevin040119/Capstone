package com.example.capstone.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.usecase.MovieUseCase

class MyListViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getAllMyList() : LiveData<List<Movie>> = movieUseCase.getAllMyList().toLiveData()
}