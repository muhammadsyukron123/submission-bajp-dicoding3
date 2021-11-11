package com.syukron.submission1_bajp_dicoding.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.databinding.ItemRowMoviesBinding
import com.syukron.submission1_bajp_dicoding.ui.detailtvshow.DetailTvShowActivity

class AdapterTvShow : PagedListAdapter<EntityTvShow, AdapterTvShow.SeriesViewHolder>(DIFF_CALLBACK) {



    class SeriesViewHolder(private val binding: ItemRowMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(serial: EntityTvShow) {
            with(binding)
            {
                tvTitle.text = serial.title
                tvGenre.text = serial.score
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV, serial.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + serial.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_refresh)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgItemPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val itemsRowMovieBinding =
            ItemRowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(itemsRowMovieBinding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val tvSerial = getItem(position)
        if (tvSerial != null) {
            holder.bind(tvSerial)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EntityTvShow>() {
            override fun areItemsTheSame(
                oldItem: EntityTvShow,
                newItem: EntityTvShow
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: EntityTvShow,
                newItem: EntityTvShow
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}