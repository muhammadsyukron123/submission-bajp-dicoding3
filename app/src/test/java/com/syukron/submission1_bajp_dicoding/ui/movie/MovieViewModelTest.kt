package com.syukron.submission1_bajp_dicoding.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository
import com.syukron.submission1_bajp_dicoding.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<EntityMovie>>>

    @Mock
    private lateinit var pagedList: PagedList<EntityMovie>

    @Before
    fun init() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(20)
        val movieData = MutableLiveData<Resource<PagedList<EntityMovie>>>()
        movieData.value = dummyMovies

        Mockito.`when`(movieRepository.getMovie()).thenReturn(movieData)
        val movieDatas = viewModel.getMovies().value?.data
        Mockito.verify(movieRepository).getMovie()
        assertNotNull(movieDatas)
        assertEquals(20, movieDatas?.size)

        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}