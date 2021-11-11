package com.syukron.submission1_bajp_dicoding.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository
import com.syukron.submission1_bajp_dicoding.utils.DummyData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = DummyData.generateDummyMovies()[0]
    private val dummyMovieId = dummyMovies.id
    private val dummyDetailMovie = DummyData.generateDummyDetailMovie(dummyMovieId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<EntityMovie>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(dummyMovieId)

    }

    @Test
    fun getSelectedMovie() {
        val movie = MutableLiveData<EntityMovie>()
        movie.value = dummyDetailMovie

        Mockito.`when`(movieRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)
        val movieEntity = viewModel.getSelectedMovie().value as EntityMovie
        Mockito.verify(movieRepository).getDetailMovie(dummyMovieId)
        assertNotNull(movieEntity)
        assertEquals(dummyDetailMovie.description, movieEntity.description)
        assertEquals(dummyDetailMovie.title, movieEntity.title)
        assertEquals(dummyDetailMovie.id, movieEntity.id)
        assertEquals(dummyDetailMovie.imagePath, movieEntity.imagePath)
        assertEquals(dummyDetailMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyDetailMovie.score, movieEntity.score)
        assertEquals(dummyDetailMovie.bookmarked, movieEntity.bookmarked)


        viewModel.getSelectedMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }
}