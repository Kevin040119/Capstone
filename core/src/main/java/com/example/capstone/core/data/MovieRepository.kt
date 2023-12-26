package com.example.capstone.core.data

import com.example.capstone.core.data.local.LocalDataSource
import com.example.capstone.core.data.remote.RemoteDataSource
import com.example.capstone.core.data.remote.Resource
import com.example.capstone.core.data.remote.network.ApiResponse
import com.example.capstone.core.domain.model.Movie
import com.example.capstone.core.domain.repository.IMovieRepository
import com.example.capstone.core.utils.AppExecutors
import com.example.capstone.core.utils.DataMapper
import io.reactivex.Flowable

class MovieRepository constructor(private val localDataSource: LocalDataSource,
                                  private val remoteDataSource: RemoteDataSource,
                                  private val appExecutors: AppExecutors
) : IMovieRepository {


    override fun getTrending(): Flowable<Resource<List<Movie>>> {
        return remoteDataSource.getTrending()
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val movies = DataMapper.mapResponsesToDomain(apiResponse.data)
                        Resource.Success(movies)
                    }
                    is ApiResponse.Empty -> {
                        Resource.Empty
                    }
                    is ApiResponse.Error -> Resource.Error(apiResponse.errorMessage)
                }
            }
            .filter { it is Resource.Success }
            .map { it as Resource<List<Movie>> }
    }

    override fun getPopular(): Flowable<Resource<List<Movie>>> {
        return remoteDataSource.getPopular()
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val movies = DataMapper.mapResponsesToDomain(apiResponse.data)
                        Resource.Success(movies)
                    }
                    is ApiResponse.Empty -> {
                        Resource.Empty
                    }
                    is ApiResponse.Error -> Resource.Error(apiResponse.errorMessage)
                }
            }
            .filter { it is Resource.Success }
            .map { it as Resource<List<Movie>> }
    }

//    override fun getPopular(): LiveData<List<Movie>> {
//        var _movie = MutableLiveData<List<Movie>>()
//        var movie : LiveData<List<Movie>> = _movie
//        var result : List<ResultsItem>? = null
//
//        val client = ApiConfig.provideApiService().getPopular()
//        client.enqueue(object : Callback<TrendingResponse> {
//            override fun onResponse(
//                call: Call<TrendingResponse>,
//                response: Response<TrendingResponse>
//            ) {
//                if(response.isSuccessful && response.body() != null) {
//                    result = (response.body()?.results as? List<ResultsItem>?)!!
//                    val resultData = DataMapper.mapResponsesToDomain(result!!)
//                    _movie.value = resultData
//                } else {
//                    Log.d(TAG, response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
//                Log.e(TAG, t.message!!)
//            }
//        })
//        return movie
//    }

    override fun insert(myList: Movie) {
        val myListEntity = DataMapper.mapDomainToEntities(myList)
        appExecutors.diskIO().execute{localDataSource.insert(myListEntity)}
    }

    override fun delete(myList: Movie) {
        val myListEntity = DataMapper.mapDomainToEntities(myList)
        appExecutors.diskIO().execute{localDataSource.delete(myListEntity)}
    }

    override fun getMyList(movieId: Int): Flowable<Movie> {
        return localDataSource.getMyList(movieId).map{ DataMapper.mapEntitiesToDomain(it)}
    }

    override fun getAllMyList(): Flowable<List<Movie>>{
        return localDataSource.getAllMyList().map{ DataMapper.mapListEntitiesToDomain(it)}
    }

//    override fun getAllMyList(): LiveData<List<Movie>> {
//        val sourceData = localDataSource.getAllMyList()
//
//        val resultLiveData = MediatorLiveData<List<Movie>>()
//
//        if(sourceData != null) {
//            resultLiveData.addSource(sourceData) {
//                resultLiveData.value = DataMapper.mapListEntitiesToDomain(it)
//            }
//        }
//
//        return resultLiveData
//    }
}