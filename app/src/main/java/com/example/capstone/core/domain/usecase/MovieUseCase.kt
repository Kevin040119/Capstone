package com.example.capstone.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.capstone.core.domain.model.Movie

interface MovieUseCase {
    fun getTrending() : LiveData<List<Movie>>

    fun getPopular() : LiveData<List<Movie>>

    fun insert(myList : Movie)
    fun delete(myList : Movie)
    fun getMyList(movieId : Int) : LiveData<Movie>
    fun getAllMyList() : LiveData<List<Movie>>
}