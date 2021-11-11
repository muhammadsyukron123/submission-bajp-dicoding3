package com.syukron.submission1_bajp_dicoding.ui.favorite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.syukron.submission1_bajp_dicoding.databinding.FragmentFavoriteTvShowBinding
import com.syukron.submission1_bajp_dicoding.ui.favorite.FavoriteViewModel
import com.syukron.submission1_bajp_dicoding.ui.tvshow.AdapterTvShow
import com.syukron.submission1_bajp_dicoding.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*


class FavoriteTvShowFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            val serialAdapter = AdapterTvShow()
            viewModel.getTvSeries().observe(viewLifecycleOwner,
                {
                    serialAdapter.submitList(it)
                    serialAdapter.notifyDataSetChanged()
                })

            with(binding.rvTvshowFragment)
            {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = serialAdapter
            }
        }
    }


}