package com.syukron.submission1_bajp_dicoding.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow

@Dao
interface DaoMovie {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPopularMovies(popularMovies: List<EntityMovie>)

    @Query("SELECT * FROM movie_entities")
    fun getAllMovies(): DataSource.Factory<Int, EntityMovie>

    @Query("SELECT * FROM movie_entities WHERE id = :movieId")
    fun getDetailMovie(movieId: String): LiveData<EntityMovie>

    @Query("SELECT * FROM movie_entities WHERE bookmarked = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, EntityMovie>

    @Update
    fun updateMovie(movieEntity: EntityMovie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPopularTvShows(popularTvSeries: List<EntityTvShow>)

    @Query("SELECT * FROM tv_show_entities")
    fun getAllTvShows(): DataSource.Factory<Int, EntityTvShow>

    @Query("SELECT * FROM tv_show_entities WHERE id = :tvSerialId")
    fun getDetailTvShow(tvSerialId: String): LiveData<EntityTvShow>

    @Query("SELECT * FROM tv_show_entities WHERE bookmarked = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, EntityTvShow>

    @Update
    fun updateTvShow(tvSerialEntity: EntityTvShow)

}