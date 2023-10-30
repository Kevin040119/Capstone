package com.example.capstone.core.data.local

import androidx.lifecycle.LiveData
import com.example.capstone.core.data.local.Room.MyListDAO
import com.example.capstone.core.data.local.entity.MyList

class LocalDataSource private constructor(private val myListDAO: MyListDAO) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: MyListDAO): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun insert(myList: MyList) = myListDAO.insert(myList)

    fun delete(myList: MyList) = myListDAO.delete(myList)

    fun getMyList(movieId : Int) : LiveData<MyList> = myListDAO.getMyList(movieId)

    fun getAllMyList() : LiveData<List<MyList>> = myListDAO.getAllMyList()
}