package com.syukron.submission1_bajp_dicoding.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository
import com.syukron.submission1_bajp_dicoding.vo.Resource

class TvShowViewModel (val movieRepository: DataRepository) : ViewModel() {
    fun getSeries(): LiveData<Resource<PagedList<EntityTvShow>>> = movieRepository.getTVShow()
}