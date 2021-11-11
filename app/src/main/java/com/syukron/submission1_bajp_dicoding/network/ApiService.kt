package com.syukron.submission1_bajp_dicoding.network

import com.syukron.submission1_bajp_dicoding.BuildConfig
import com.syukron.submission1_bajp_dicoding.data.MoviesResponse
import com.syukron.submission1_bajp_dicoding.data.TVShowsResponse
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("popular?api_key=${BuildConfig.API_KEY}&page=1")
    fun getMovie(
    ): Call<MoviesResponse>

    @GET("{id}?api_key=${BuildConfig.API_KEY}")
    fun getDetailMovie(
        @Path("id") id: String
    ): Call<EntityMovie>

    @GET("popular?api_key=${BuildConfig.API_KEY}&page=1")
    fun getTvSerial(
    ): Call<TVShowsResponse>

    @GET("{id}?api_key=${BuildConfig.API_KEY}")
    fun getDetailTVSerial(
        @Path("id") id: String
    ): Call<EntityTvShow>


}