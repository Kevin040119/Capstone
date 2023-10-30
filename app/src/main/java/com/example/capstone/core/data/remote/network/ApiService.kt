package com.example.capstone.core.data.remote.network

import com.example.capstone.core.data.remote.response.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("3/trending/movie/day")
    fun getTrending(
        @Header("Authorization") authorization : String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZDQ2ODdkNjNmZWI0ZTAzMzI1YjQ3MTQ3NjY0ZTJmMiIsInN1YiI6IjY1MmExYzgyMGNiMzM1MTZmNjNlMjNhMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4eAgZeSavbEuJBc4X2vXUI1XOJ94BsBHFv93j6kl1SA",
        @Header("accept") accept : String = "application/json",
        @Query("language") language : String = "en-US"
    ) : Call<TrendingResponse>

    @GET("3/movie/popular")
    fun getPopular(
        @Header("Authorization") authorization : String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZDQ2ODdkNjNmZWI0ZTAzMzI1YjQ3MTQ3NjY0ZTJmMiIsInN1YiI6IjY1MmExYzgyMGNiMzM1MTZmNjNlMjNhMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4eAgZeSavbEuJBc4X2vXUI1XOJ94BsBHFv93j6kl1SA",
        @Header("accept") accept : String = "application/json"
    ) : Call<TrendingResponse>
}