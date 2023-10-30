package com.example.capstone.Home

import androidx.lifecycle.ViewModel
import com.example.capstone.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    var trend = movieUseCase.getTrending()
    var popular = movieUseCase.getPopular()
    var myList = movieUseCase.getAllMyList()
}