package com.example.capstone.core.data.local

import com.example.capstone.core.data.local.Room.MyListDAO
import com.example.capstone.core.data.local.entity.MyList
import io.reactivex.Flowable

class LocalDataSource (private val myListDAO: MyListDAO) {

    //Koin
//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(tourismDao: MyListDAO): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(tourismDao)
//            }
//    }

    fun insert(myList: MyList) = myListDAO.insert(myList)

    fun delete(myList: MyList) = myListDAO.delete(myList)

    fun getMyList(movieId : Int) : Flowable<MyList> = myListDAO.getMyList(movieId)

    fun getAllMyList() : Flowable<List<MyList>> = myListDAO.getAllMyList()
}