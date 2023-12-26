package com.example.capstone.core.domain.usecase

import com.example.capstone.core.data.remote.Resource
import com.example.capstone.core.domain.model.Movie
import io.reactivex.Flowable

interface MovieUseCase {
    fun getTrending() : Flowable<Resource<List<Movie>>>

    fun getPopular() : Flowable<Resource<List<Movie>>>

    fun insert(myList : Movie)
    fun delete(myList : Movie)
    fun getMyList(movieId : Int) : Flowable<Movie>
    fun getAllMyList() : Flowable<List<Movie>>
}