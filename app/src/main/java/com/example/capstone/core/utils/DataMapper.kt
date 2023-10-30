package com.example.capstone.core.utils

import com.example.capstone.core.data.local.entity.MyList
import com.example.capstone.core.data.remote.response.ResultsItem
import com.example.capstone.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToDomain(input: List<ResultsItem>): List<Movie> =
        input.map {
            Movie(
                movieId = it.id!!,
                movieTitle = it.title!!,
                movieDescription = it.overview!!,
                movieGenre = it.genreIds!!,
                movieLanguage = it.originalLanguage!!,
                moviePoster = it.posterPath!!,
                movieRelease = it.releaseDate!!,
                movieVote = it.voteAverage.toString(),
                movieVoteCount = it.voteCount!!,
                movieBackdrop = it.backdropPath!!
            )
        }

    fun mapEntitiesToDomain(input : MyList) : Movie =
        Movie(
            movieId = input.movieId!!,
            movieTitle = input.movieTitle!!,
            movieDescription = input.movieDescription!!,
            movieLanguage = input.movieLanguage!!,
            moviePoster = input.moviePoster!!,
            movieRelease = input.movieRelease!!,
            movieVoteCount = input.movieVoteCount!!,
            movieVote = input.movieVote!!,
            movieBackdrop = input.movieBackdrop!!
        )

    fun mapDomainToEntities(input : Movie) : MyList =
        MyList(
            movieId = input.movieId,
            movieTitle = input.movieTitle,
            movieDescription = input.movieDescription,
            movieLanguage = input.movieLanguage,
            moviePoster = input.moviePoster,
            movieRelease = input.movieRelease,
            movieVoteCount = input.movieVoteCount,
            movieVote = input.movieVote,
            movieBackdrop = input.movieBackdrop
        )

    fun mapListEntitiesToDomain(input : List<MyList>) : List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId!!,
                movieTitle = it.movieTitle!!,
                movieDescription = it.movieDescription!!,
                movieLanguage = it.movieLanguage!!,
                moviePoster = it.moviePoster!!,
                movieRelease = it.movieRelease!!,
                movieVoteCount = it.movieVoteCount!!,
                movieVote = it.movieVote!!,
                movieBackdrop = it.movieBackdrop!!
            )
        }
}