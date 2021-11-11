package com.syukron.submission1_bajp_dicoding.ui.detailmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.databinding.ActivityDetailMovieBinding
import com.syukron.submission1_bajp_dicoding.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var movieEntity: EntityMovie
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieSelected = intent.getStringExtra(EXTRA_MOVIE)
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(applicationContext)
        )[DetailMovieViewModel::class.java]
        if (movieSelected != null) {
            viewModel.setSelectedMovie(movieSelected)
            viewModel.getSelectedMovie().observe(this,
                {
                    movieEntity = it
                    inflateActivity(movieEntity)
                    SetFloatingButtonFavorite(movieEntity.bookmarked)
                })

        }
        detailMovieBinding.fabFavoriteMovie.setOnClickListener {
            movieEntity.bookmarked = !movieEntity.bookmarked
            viewModel.setMovieBookmark(movieEntity, movieEntity.bookmarked)
        }

    }

    private fun inflateActivity(movieEntity: EntityMovie) {
        detailMovieBinding.tvDetailMovieTitle.text = movieEntity.title
        detailMovieBinding.tvDetailMovieYear.text = movieEntity.releaseDate
        detailMovieBinding.tvDetailMovieGenre.text = movieEntity.score
        detailMovieBinding.tvDetailMovieOverview.text = movieEntity.description
        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movieEntity.imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_refresh)
                    .error(R.drawable.ic_broken_image)
            )
            .into(detailMovieBinding.ivDetailMoviePoster)

        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movieEntity.imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_refresh)
                    .error(R.drawable.ic_broken_image)
            )
            .into(detailMovieBinding.ivDetailMovieBg)
    }

    private fun SetFloatingButtonFavorite(state: Boolean) {
        val floatButton = detailMovieBinding.fabFavoriteMovie
        if (state) {
            floatButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star
                )
            )
        } else {
            floatButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star_border
                )
            )

        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

}