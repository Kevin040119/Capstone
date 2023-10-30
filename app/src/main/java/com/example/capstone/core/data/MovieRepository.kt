package com.example.capstone.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.core.data.local.LocalDataSource
import com.example.capstone.core.data.remote.network.ApiConfig
import com.example.capstone.core.data.remote.response.ResultsItem
import com.example.capstone.core.data.remote.response.TrendingResponse
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.repository.IMovieRepository
import com.example.capstone.core.utils.AppExecutors
import com.example.capstone.core.utils.DataMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val localDataSource: LocalDataSource,
                      private val appExecutors: AppExecutors
) : IMovieRepository{

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(localData : LocalDataSource,
                        appExecutors: AppExecutors) : MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(localData, appExecutors)
            }
        const val TAG = "tag"
    }

    override fun getTrending() : LiveData<List<Movie>> {
        var _movie = MutableLiveData<List<Movie>>()
        var movie : LiveData<List<Movie>> = _movie
        var result : List<ResultsItem>? = null

        val client = ApiConfig.provideApiService().getTrending()
        client.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if(response.isSuccessful && response.body() != null) {
                    result = (response.body()?.results as? List<ResultsItem>?)!!
                    val resultData = DataMapper.mapResponsesToDomain(result!!)
                    _movie.value = resultData
                } else {
                    Log.d(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                Log.e(TAG, t.message!!)
            }
        })
        return movie
    }

    override fun getPopular(): LiveData<List<Movie>> {
        var _movie = MutableLiveData<List<Movie>>()
        var movie : LiveData<List<Movie>> = _movie
        var result : List<ResultsItem>? = null

        val client = ApiConfig.provideApiService().getPopular()
        client.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if(response.isSuccessful && response.body() != null) {
                    result = (response.body()?.results as? List<ResultsItem>?)!!
                    val resultData = DataMapper.mapResponsesToDomain(result!!)
                    _movie.value = resultData
                } else {
                    Log.d(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                Log.e(TAG, t.message!!)
            }
        })
        return movie
    }

    override fun insert(myList: Movie) {
        val myListEntity = DataMapper.mapDomainToEntities(myList)
        appExecutors.diskIO().execute{localDataSource.insert(myListEntity)}
    }

    override fun delete(myList: Movie) {
        val myListEntity = DataMapper.mapDomainToEntities(myList)
        appExecutors.diskIO().execute{localDataSource.delete(myListEntity)}
    }

    //Error disini
    override fun getMyList(movieId: Int): LiveData<Movie> {

        val sourceLiveData = localDataSource.getMyList(movieId)
        val resultLiveData = MediatorLiveData<Movie>()

        if(sourceLiveData != null) {
            resultLiveData.addSource(sourceLiveData) { myListEntity ->
                if(myListEntity != null) {
                    resultLiveData.value = DataMapper.mapEntitiesToDomain(myListEntity!!)
                }
            }
        } else {
            resultLiveData.value = Movie()
        }

        return resultLiveData
    }

    override fun getAllMyList(): LiveData<List<Movie>> {
        val sourceData = localDataSource.getAllMyList()

        val resultLiveData = MediatorLiveData<List<Movie>>()

        if(sourceData != null) {
            resultLiveData.addSource(sourceData) {
                resultLiveData.value = DataMapper.mapListEntitiesToDomain(it)
            }
        }

        return resultLiveData
    }
}