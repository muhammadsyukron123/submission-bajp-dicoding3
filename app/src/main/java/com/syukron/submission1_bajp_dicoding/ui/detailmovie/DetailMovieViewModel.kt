package com.syukron.submission1_bajp_dicoding.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository

class DetailMovieViewModel(private val movieRepository: DataRepository) : ViewModel() {
    lateinit var movieId: String
    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getSelectedMovie(): LiveData<EntityMovie> {
        return movieRepository.getDetailMovie(movieId)
    }

    fun setMovieBookmark(movieEntity: EntityMovie, state: Boolean) {
        movieRepository.setMovieFavorite(movieEntity, state)
    }

}