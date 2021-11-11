package com.syukron.submission1_bajp_dicoding.di

import android.content.Context
import com.syukron.submission1_bajp_dicoding.data.database.DatabaseMovieRoom
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository
import com.syukron.submission1_bajp_dicoding.data.source.LocalDataSource
import com.syukron.submission1_bajp_dicoding.data.source.response.RemoteDataSource
import com.syukron.submission1_bajp_dicoding.utils.AppExecutors
import com.syukron.submission1_bajp_dicoding.utils.CallApiHelper

object Injection {

    fun provideRepository(context: Context): DataRepository {
        val database = DatabaseMovieRoom.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(CallApiHelper())
        val appExecutors = AppExecutors()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())

        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}