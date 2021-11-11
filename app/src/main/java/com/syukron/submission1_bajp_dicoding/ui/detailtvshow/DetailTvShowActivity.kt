package com.syukron.submission1_bajp_dicoding.ui.detailtvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.databinding.ActivityDetailTvShowBinding
import com.syukron.submission1_bajp_dicoding.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var tvShowEntity: EntityTvShow
    private lateinit var binding: ActivityDetailTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(applicationContext)
        )[DetailTvShowViewModel::class.java]
        val tvShowsSelected = intent.getStringExtra(EXTRA_TV)
        if (tvShowsSelected != null) {
            viewModel.setSelectedTVShow(tvShowsSelected)
            viewModel.getSelectedTVShow().observe(this, {
                tvShowEntity = it
                inflateActivity(tvShowEntity)
                SetFloatingButtonFavorite(tvShowEntity.bookmarked)
            })
        }
        binding.fabFavoriteTvshow.setOnClickListener {
            tvShowEntity.bookmarked = !tvShowEntity.bookmarked
            viewModel.setTvShowBookmark(tvShowEntity, tvShowEntity.bookmarked)
        }

    }

    private fun inflateActivity(entity: EntityTvShow) {
        binding.tvDetailMovieTitle.text = entity.title
        binding.tvDetailMovieYear.text = entity.releaseDate
        binding.tvDetailMovieGenre.text = entity.score
        binding.tvDetailMovieOverview.text = entity.description
        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + entity.imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_refresh)
                    .error(R.drawable.ic_broken_image)
            )
            .into(binding.ivDetailMoviePoster)

        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + entity.imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_refresh)
                    .error(R.drawable.ic_broken_image)
            )
            .into(binding.ivDetailMovieBg)
    }

    private fun SetFloatingButtonFavorite(state: Boolean) {
        val floatButton = binding.fabFavoriteTvshow
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
        const val EXTRA_TV = "extra_tv_shows"
    }
}