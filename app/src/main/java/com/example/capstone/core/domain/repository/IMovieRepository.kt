package com.example.capstone.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.capstone.core.domain.model.Movie

interface IMovieRepository {
    fun getTrending() : LiveData<List<Movie>>
    fun getPopular() : LiveData<List<Movie>>
    fun insert(myList : Movie)
    fun delete(myList : Movie)
    fun getMyList(movieId : Int) : LiveData<Movie>
    fun getAllMyList() : LiveData<List<Movie>>
}