package com.example.capstone.core.di

import android.app.Application
import android.content.Context
import com.example.capstone.core.data.MovieRepository
import com.example.capstone.core.data.local.LocalDataSource
import com.example.capstone.core.data.local.Room.MyListRoomDatabase
import com.example.capstone.core.domain.repository.IMovieRepository
import com.example.capstone.core.domain.usecase.MovieInteractor
import com.example.capstone.core.domain.usecase.MovieUseCase
import com.example.capstone.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context) : IMovieRepository {
        val database = MyListRoomDatabase.getDatabase(context)
        val localDataSource = LocalDataSource.getInstance(database.MyListDAO())
        val appExecutors = AppExecutors()

        return MovieRepository(localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}