package com.syukron.submission1_bajp_dicoding.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.data.source.response.RemoteDataSource
import com.syukron.submission1_bajp_dicoding.util.LiveDataTestUtil
import com.syukron.submission1_bajp_dicoding.util.PagedListUtil
import com.syukron.submission1_bajp_dicoding.utils.AppExecutors
import com.syukron.submission1_bajp_dicoding.utils.DummyData
import com.syukron.submission1_bajp_dicoding.vo.Resource
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val movieRepository = FakeDataRepository(remote, local, appExecutors)
    private val movieResponses = DummyData.generateDummyMovies()
    private val movieId = movieResponses[0].id
    private val detailMovieResponse = DummyData.generateDummyDetailMovie(movieId)

    private val tvSeriesRepository = FakeDataRepository(remote, local, appExecutors)
    private val tvSerialResponse = DummyData.generateDummySeries()
    private val tvSerialId = tvSerialResponse[0].id
    private val detailTVSerialResponse = DummyData.generateDummyDetailTVSerial(tvSerialId)

    @Test
    fun getMovie() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityMovie>
        Mockito.`when`(local.getAllPopularMovies()).thenReturn(dataSourceFactory)
        movieRepository.getMovie()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()))
        verify(local).getAllPopularMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<EntityMovie>()
        dummyMovie.value = DummyData.generateDummyDetailMovie(movieId)
        Mockito.`when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(detailMovieResponse.title, movieEntity.title)
        assertEquals(detailMovieResponse.description, movieEntity.description)
        assertEquals(detailMovieResponse.score, movieEntity.score)
        assertEquals(detailMovieResponse.releaseDate, movieEntity.releaseDate)
        assertEquals(detailMovieResponse.imagePath, movieEntity.imagePath)
        assertEquals(detailMovieResponse.id, movieEntity.id)
        assertEquals(detailMovieResponse.bookmarked, movieEntity.bookmarked)
    }


    @Test
    fun getFavoritedMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityMovie>
        Mockito.`when`(local.getFavoritedMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoritedMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()))
        verify(local).getFavoritedMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())

    }

    @Test
    fun getTVSerial() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityTvShow>
        Mockito.`when`(local.getAllPopularTvShows()).thenReturn(dataSourceFactory)
        tvSeriesRepository.getTVShow()

        val tvSeriesEntity =
            Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummySeries()))
        verify(local).getAllPopularTvShows()
        assertNotNull(tvSeriesEntity.data)
        assertEquals(tvSerialResponse.size.toLong(), tvSeriesEntity.data?.size?.toLong())

    }

    @Test
    fun getDetailTVSerial() {

        val dummyTvshow = MutableLiveData<EntityTvShow>()
        dummyTvshow.value = DummyData.generateDummyDetailTVSerial(tvSerialId)
        Mockito.`when`(local.getTvShowDetail(tvSerialId)).thenReturn(dummyTvshow)

        val tvSerialEntity =
            LiveDataTestUtil.getValue(tvSeriesRepository.getDetailTVShow(tvSerialId))
        verify(local).getTvShowDetail(tvSerialId)
        assertNotNull(tvSerialEntity)
        assertEquals(detailTVSerialResponse.title, tvSerialEntity.title)
        assertEquals(detailTVSerialResponse.description, tvSerialEntity.description)
        assertEquals(detailTVSerialResponse.score, tvSerialEntity.score)
        assertEquals(detailTVSerialResponse.releaseDate, tvSerialEntity.releaseDate)
        assertEquals(detailTVSerialResponse.imagePath, tvSerialEntity.imagePath)
        assertEquals(detailTVSerialResponse.id, tvSerialEntity.id)
        assertEquals(detailTVSerialResponse.bookmarked, tvSerialEntity.bookmarked)

    }

    @Test
    fun getFavoritedTvSerial() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityTvShow>
        Mockito.`when`(local.getFavoritedTvShows()).thenReturn(dataSourceFactory)
        tvSeriesRepository.getFavoritedTvShow()

        val tvSeriesEntity =
            Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummySeries()))
        verify(local).getFavoritedTvShows()
        assertNotNull(tvSeriesEntity)
        assertEquals(tvSerialResponse.size.toLong(), tvSeriesEntity.data?.size?.toLong())
    }
}