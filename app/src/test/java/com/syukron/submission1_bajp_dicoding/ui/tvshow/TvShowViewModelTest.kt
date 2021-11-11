package com.syukron.submission1_bajp_dicoding.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
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
class TvShowViewModelTest {


    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvSerialRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<EntityTvShow>>>

    @Mock
    private lateinit var pagedList: PagedList<EntityTvShow>

    @Before
    fun init() {
        viewModel = TvShowViewModel(tvSerialRepository)
    }

    @Test
    fun getSeries() {

        val dummyTvSeries = Resource.success(pagedList)
        Mockito.`when`(dummyTvSeries.data?.size).thenReturn(20)
        val tvSerial = MutableLiveData<Resource<PagedList<EntityTvShow>>>()
        tvSerial.value = dummyTvSeries

        Mockito.`when`(tvSerialRepository.getTVShow()).thenReturn(tvSerial)
        val tvSerialEntities = viewModel.getSeries().value?.data
        Mockito.verify(tvSerialRepository).getTVShow()
        assertNotNull(tvSerialEntities)
        assertEquals(20, tvSerialEntities?.size)

        viewModel.getSeries().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvSeries)
    }
}