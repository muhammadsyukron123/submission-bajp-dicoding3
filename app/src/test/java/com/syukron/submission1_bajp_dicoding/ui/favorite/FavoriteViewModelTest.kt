package com.syukron.submission1_bajp_dicoding.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<EntityMovie>>

    @Mock
    private lateinit var pagedListMovie: PagedList<EntityMovie>

    @Mock
    private lateinit var tvSerialObserver: Observer<PagedList<EntityTvShow>>

    @Mock
    private lateinit var pagedListTvSerial: PagedList<EntityTvShow>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }


    @Test
    fun getMovies() {

        val dummyMovie = pagedListMovie
        Mockito.`when`(dummyMovie.size).thenReturn(20)
        val movie = MutableLiveData<PagedList<EntityMovie>>()
        movie.value = dummyMovie

        Mockito.`when`(movieRepository.getFavoritedMovies()).thenReturn(movie)

        val movieEntities = viewModel.getMovies().value
        verify(movieRepository).getFavoritedMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvSeries() {

        val dummyTvshow = pagedListTvSerial
        Mockito.`when`(dummyTvshow.size).thenReturn(20)
        val tvSerial = MutableLiveData<PagedList<EntityTvShow>>()
        tvSerial.value = dummyTvshow

        Mockito.`when`(movieRepository.getFavoritedTvShow()).thenReturn(tvSerial)
        val tvSerialEntities = viewModel.getTvSeries().value
        verify(movieRepository).getFavoritedTvShow()
        assertNotNull(tvSerialEntities)
        assertEquals(20, tvSerialEntities?.size)

        viewModel.getTvSeries().observeForever(tvSerialObserver)
        verify(tvSerialObserver).onChanged(dummyTvshow)

    }
}