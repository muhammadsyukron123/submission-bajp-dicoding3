package com.syukron.submission1_bajp_dicoding.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository
import com.syukron.submission1_bajp_dicoding.vo.Resource

class MovieViewModel (val movieRepository: DataRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<EntityMovie>>> = movieRepository.getMovie()
}