package com.syukron.submission1_bajp_dicoding.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository

class FavoriteViewModel (private val movieRepository: DataRepository) : ViewModel() {
    fun getMovies(): LiveData<PagedList<EntityMovie>> = movieRepository.getFavoritedMovies()
    fun getTvSeries(): LiveData<PagedList<EntityTvShow>> = movieRepository.getFavoritedTvShow()
}