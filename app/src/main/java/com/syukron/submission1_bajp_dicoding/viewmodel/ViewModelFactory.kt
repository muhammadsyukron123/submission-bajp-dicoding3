package com.syukron.submission1_bajp_dicoding.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syukron.submission1_bajp_dicoding.data.source.DataRepository
import com.syukron.submission1_bajp_dicoding.di.Injection
import com.syukron.submission1_bajp_dicoding.ui.detailmovie.DetailMovieViewModel
import com.syukron.submission1_bajp_dicoding.ui.detailtvshow.DetailTvShowViewModel
import com.syukron.submission1_bajp_dicoding.ui.favorite.FavoriteViewModel
import com.syukron.submission1_bajp_dicoding.ui.movie.MovieViewModel
import com.syukron.submission1_bajp_dicoding.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mMovieRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {



    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }
}