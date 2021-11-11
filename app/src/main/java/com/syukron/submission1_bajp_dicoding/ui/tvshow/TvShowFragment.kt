package com.syukron.submission1_bajp_dicoding.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.syukron.submission1_bajp_dicoding.databinding.FragmentTvShowBinding
import com.syukron.submission1_bajp_dicoding.viewmodel.ViewModelFactory
import com.syukron.submission1_bajp_dicoding.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_show.*


class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowsBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvShowsBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val serialAdapter = AdapterTvShow()
            viewModel.getSeries().observe(viewLifecycleOwner, { tvSerial ->
                if (tvSerial != null) {
                    when (tvSerial.status) {
                        Status.LOADING -> fragmentTvShowsBinding.pbTvshow.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvShowsBinding.pbTvshow.visibility = View.GONE
                            serialAdapter.submitList(tvSerial.data)
                            serialAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTvShowsBinding.pbTvshow.visibility = View.GONE
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        }

                    }
                }

            })


            with(fragmentTvShowsBinding.rvTvshowFragment)
            {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = serialAdapter
            }
        }
    }
}