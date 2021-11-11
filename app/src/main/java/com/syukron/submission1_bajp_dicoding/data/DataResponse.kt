package com.syukron.submission1_bajp_dicoding.data

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @field:SerializedName("results")
    val movie: List<MovieResponse>,

    )

data class MovieResponse(
    @SerializedName("original_title")
    var title: String = "",

    @SerializedName("release_date")
    var releaseDate: String = "",

    @SerializedName("vote_average")
    var score: String = "",

    @SerializedName("overview")
    var description: String = "",

    @SerializedName("poster_path")
    var imagePath: String = "",

    @SerializedName("id")
    var id: String = ""
)


data class TVShowsResponse(

    @field:SerializedName("results")
    val movie: List<TvShowResponse>,

    )

data class TvShowResponse(
    @SerializedName("original_name")
    var title: String = "",
    @SerializedName("first_air_date")
    var releaseDate: String = "",
    @SerializedName("vote_average")
    var score: String = "",
    @SerializedName("overview")
    var description: String = "",
    @SerializedName("poster_path")
    var imagePath: String = "",
    @SerializedName("id")
    var id: String = ""
)
