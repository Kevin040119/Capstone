package com.example.capstone.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MyList(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null,

    @ColumnInfo(name = "movieId")
    var movieId : Int? = null,

    @ColumnInfo(name = "movieTitle")
    var movieTitle : String? = null,

    @ColumnInfo(name = "movieLanguage")
    var movieLanguage : String? = null,

    @ColumnInfo(name = "movieDescription")
    var movieDescription : String? = null,

    @ColumnInfo(name = "moviePoster")
    var moviePoster : String? = null,

    @ColumnInfo(name = "movieRelease")
    var movieRelease : String? = null,

    @ColumnInfo(name = "movieVote")
    var movieVote : String? = null,

    @ColumnInfo(name = "movieVoteCount")
    var movieVoteCount : Int? = null,

    @ColumnInfo(name = "movieBackdrop")
    var movieBackdrop : String? = null
) : Parcelable