package com.syukron.submission1_bajp_dicoding.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.vo.Resource

interface MovieDataSource {
    fun getMovie(): LiveData<Resource<PagedList<EntityMovie>>>

    fun getDetailMovie(id: String): LiveData<EntityMovie>

    fun getTVShow(): LiveData<Resource<PagedList<EntityTvShow>>>

    fun getDetailTVShow(id: String): LiveData<EntityTvShow>

    fun setMovieFavorite(movieFavorited: EntityMovie, state: Boolean)

    fun getFavoritedMovies(): LiveData<PagedList<EntityMovie>>

    fun setTvShowFavorite(tvSerialFavorited: EntityTvShow, state: Boolean)

    fun getFavoritedTvShow(): LiveData<PagedList<EntityTvShow>>
}