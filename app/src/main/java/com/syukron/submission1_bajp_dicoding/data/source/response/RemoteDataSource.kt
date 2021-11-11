package com.syukron.submission1_bajp_dicoding.data.source.response

import androidx.lifecycle.LiveData
import com.syukron.submission1_bajp_dicoding.data.MovieResponse
import com.syukron.submission1_bajp_dicoding.data.TvShowResponse
import com.syukron.submission1_bajp_dicoding.data.source.remote.ApiResponse
import com.syukron.submission1_bajp_dicoding.utils.CallApiHelper
import com.syukron.submission1_bajp_dicoding.utils.EspressoIdlingResource

class RemoteDataSource(private val apiHelper: CallApiHelper) {

    fun getMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        return apiHelper.loadMovies()
    }


    fun getTVShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        return apiHelper.loadTVShows()
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: CallApiHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }
}