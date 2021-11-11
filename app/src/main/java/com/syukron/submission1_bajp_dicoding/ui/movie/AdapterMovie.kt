package com.syukron.submission1_bajp_dicoding.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.databinding.ItemRowMoviesBinding
import com.syukron.submission1_bajp_dicoding.ui.detailmovie.DetailMovieActivity

class AdapterMovie : PagedListAdapter<EntityMovie, AdapterMovie.MovieViewHolder>(DIFF_CALLBACK) {

    class MovieViewHolder(private val binding: ItemRowMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: EntityMovie) {
            with(binding)
            {
                tvTitle.text = movie.title
                tvGenre.text = movie.score
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + (movie.imagePath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_refresh)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgItemPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
            ItemRowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EntityMovie>() {
            override fun areItemsTheSame(oldItem: EntityMovie, newItem: EntityMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: EntityMovie, newItem: EntityMovie): Boolean {
                return oldItem == newItem
            }
        }
    }

}