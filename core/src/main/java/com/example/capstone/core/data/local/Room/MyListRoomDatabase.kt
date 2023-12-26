package com.example.capstone.core.data.local.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.capstone.core.data.local.entity.MyList

@Database(entities = [MyList::class], version = 5, exportSchema = false)
abstract class MyListRoomDatabase : RoomDatabase() {
    abstract fun MyListDAO() : MyListDAO

}