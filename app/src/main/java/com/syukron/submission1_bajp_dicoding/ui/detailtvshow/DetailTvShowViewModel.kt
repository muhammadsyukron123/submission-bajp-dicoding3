package com.syukron.submission1_bajp_dicoding.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository

class DetailTvShowViewModel (private val movieRepository: DataRepository) : ViewModel() {

    lateinit var movieId: String
    fun setSelectedTVShow(movieId: String) {
        this.movieId = movieId
    }

    fun getSelectedTVShow(): LiveData<EntityTvShow> {
        return movieRepository.getDetailTVShow(movieId)
    }

    fun setTvShowBookmark(tvSerial: EntityTvShow, state: Boolean) {
        movieRepository.setTvShowFavorite(tvSerial, state)
    }


}