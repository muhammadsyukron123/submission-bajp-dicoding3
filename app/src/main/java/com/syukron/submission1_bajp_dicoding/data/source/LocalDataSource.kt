package com.syukron.submission1_bajp_dicoding.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.syukron.submission1_bajp_dicoding.data.database.DaoMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow

class LocalDataSource private constructor(private val mMovieDao: DaoMovie) {

    fun insertPopularMovies(popularMovies: List<EntityMovie>) =
        mMovieDao.insertPopularMovies(popularMovies)

    fun getAllPopularMovies(): DataSource.Factory<Int, EntityMovie> = mMovieDao.getAllMovies()
    fun getMovieDetail(movieId: String): LiveData<EntityMovie> = mMovieDao.getDetailMovie(movieId)
    fun getFavoritedMovies(): DataSource.Factory<Int, EntityMovie> = mMovieDao.getFavoriteMovies()
    fun setMovieBookmark(movie: EntityMovie, newState: Boolean) {
        movie.bookmarked = newState
        mMovieDao.updateMovie(movie)
    }

    fun insertPopularTvShows(popularTvSeries: List<EntityTvShow>) =
        mMovieDao.insertPopularTvShows(popularTvSeries)

    fun getAllPopularTvShows(): DataSource.Factory<Int, EntityTvShow> =
        mMovieDao.getAllTvShows()

    fun getTvShowDetail(tvShowId: String): LiveData<EntityTvShow> =
        mMovieDao.getDetailTvShow(tvShowId)

    fun getFavoritedTvShows(): DataSource.Factory<Int, EntityTvShow> =
        mMovieDao.getFavoriteTvShows()

    fun setTvSerialBookmark(tvShow: EntityTvShow, newState: Boolean) {
        tvShow.bookmarked = newState
        mMovieDao.updateTvShow(tvShow)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: DaoMovie): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }
}