package com.example.capstone.core.data.local.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstone.core.data.local.entity.MyList

@Database(entities = [MyList::class], version = 5, exportSchema = false)
abstract class MyListRoomDatabase : RoomDatabase() {
    abstract fun MyListDAO() : MyListDAO

    companion object {
        @Volatile
        private var INSTANCE : MyListRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context : Context) : MyListRoomDatabase {
            if(INSTANCE == null) {
                synchronized(MyListRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MyListRoomDatabase::class.java, "mylist_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as MyListRoomDatabase
        }
    }
}