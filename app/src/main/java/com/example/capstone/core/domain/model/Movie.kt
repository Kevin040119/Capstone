package com.example.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var movieId: Int? = null,
    val movieTitle: String? = null,
    val movieLanguage: String? = null,
    val movieDescription: String? = null,
    val movieGenre: List<Int?>? = null,
    val moviePoster: String? = null,
    val movieRelease: String? = null,
    val movieVote: String? = null,
    val movieVoteCount: Int? = null,
    val movieBackdrop : String? = null
) : Parcelable
