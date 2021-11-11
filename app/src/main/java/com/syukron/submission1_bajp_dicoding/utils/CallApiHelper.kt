package com.syukron.submission1_bajp_dicoding.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syukron.submission1_bajp_dicoding.data.MovieResponse
import com.syukron.submission1_bajp_dicoding.data.MoviesResponse
import com.syukron.submission1_bajp_dicoding.data.TVShowsResponse
import com.syukron.submission1_bajp_dicoding.data.TvShowResponse
import com.syukron.submission1_bajp_dicoding.data.source.remote.ApiResponse
import com.syukron.submission1_bajp_dicoding.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallApiHelper {
    fun loadMovies(): MutableLiveData<ApiResponse<List<MovieResponse>>> {
        val serviceMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        val postService = ApiConfig.getApiService().getMovie()
        postService.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                serviceMovie.value = response.body()?.let {
                    ApiResponse.success(it.movie)
                }
                if (!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                if (!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
                Log.d("Error", t.message.toString())
            }
        }
        )
        return serviceMovie
    }

    fun loadTVShows(): MutableLiveData<ApiResponse<List<TvShowResponse>>> {

        val serviceTVSeries = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        val postService = ApiConfig.getApiServiceTV().getTvSerial()

        postService.enqueue(object : Callback<TVShowsResponse> {
            override fun onResponse(
                call: Call<TVShowsResponse>,
                response: Response<TVShowsResponse>
            ) {
                serviceTVSeries.value = response.body()?.let {
                    ApiResponse.success(it.movie)
                }
                if (!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                Log.d("Error", t.message.toString())
                if (!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

        })

        return serviceTVSeries

    }
}