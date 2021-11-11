package com.syukron.submission1_bajp_dicoding.ui.detailtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
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
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTVSerial = DummyData.generateDummySeries()[0]
    private val tvSerialId = dummyTVSerial.id
    private val dummyDetailTVSerial = DummyData.generateDummyDetailTVSerial(tvSerialId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvSerialRepository: DataRepository

    @Mock
    private lateinit var tvSerialObserver: Observer<EntityTvShow>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(tvSerialRepository)
        viewModel.setSelectedTVShow(tvSerialId)

    }

    @Test
    fun getSelectedTVSerial() {
        val tvSerial = MutableLiveData<EntityTvShow>()
        tvSerial.value = dummyDetailTVSerial

        Mockito.`when`(tvSerialRepository.getDetailTVShow(tvSerialId)).thenReturn(tvSerial)
        val tvSerialEntity = viewModel.getSelectedTVShow().value as EntityTvShow
        Mockito.verify(tvSerialRepository).getDetailTVShow(tvSerialId)
        assertNotNull(tvSerialEntity)
        assertEquals(dummyDetailTVSerial.description, tvSerialEntity.description)
        assertEquals(dummyDetailTVSerial.title, tvSerialEntity.title)
        assertEquals(dummyDetailTVSerial.id, tvSerialEntity.id)
        assertEquals(dummyDetailTVSerial.imagePath, tvSerialEntity.imagePath)
        assertEquals(dummyDetailTVSerial.releaseDate, tvSerialEntity.releaseDate)
        assertEquals(dummyDetailTVSerial.score, tvSerialEntity.score)
        assertEquals(dummyDetailTVSerial.bookmarked, tvSerialEntity.bookmarked)

        viewModel.getSelectedTVShow().observeForever(tvSerialObserver)
        verify(tvSerialObserver).onChanged(dummyDetailTVSerial)
    }
}