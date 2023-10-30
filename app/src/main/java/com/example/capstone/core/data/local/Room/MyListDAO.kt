package com.example.capstone.core.data.local.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstone.core.data.local.entity.MyList

@Dao
interface MyListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(myList : MyList)

    @Delete
    fun delete(myList: MyList)

    @Query("SELECT * FROM MyList WHERE movieId = :movieId")
    fun getMyList(movieId : Int) : LiveData<MyList>

    @Query("SELECT * FROM MyList")
    fun getAllMyList() : LiveData<List<MyList>>
}