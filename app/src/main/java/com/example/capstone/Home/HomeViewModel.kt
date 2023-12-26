package com.example.capstone.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.capstone.core.data.remote.Resource
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.usecase.MovieUseCase

class HomeViewModel(val movieUseCase: MovieUseCase) : ViewModel() {
    fun getTrend() : LiveData<List<Movie>>{
        val originalLiveData: LiveData<Resource<List<Movie>>> = movieUseCase.getTrending().toLiveData()
        val transformedLiveData = MediatorLiveData<List<Movie>>()

        transformedLiveData.addSource(originalLiveData) { resource ->
            resource?.data?.let {
                transformedLiveData.value = it
            }
        }
        return transformedLiveData
    }

    fun getPopular() : LiveData<List<Movie>>{
        val originalLiveData: LiveData<Resource<List<Movie>>> = movieUseCase.getPopular().toLiveData()
        val transformedLiveData = MediatorLiveData<List<Movie>>()

        transformedLiveData.addSource(originalLiveData) { resource ->
            resource?.data?.let {
                transformedLiveData.value = it
            }
        }
        return transformedLiveData
    }

    var myList = movieUseCase.getAllMyList().toLiveData()
}