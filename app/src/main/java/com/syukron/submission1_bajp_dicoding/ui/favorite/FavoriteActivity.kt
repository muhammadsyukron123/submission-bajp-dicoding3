package com.syukron.submission1_bajp_dicoding.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.databinding.ActivityFavoriteBinding
import com.syukron.submission1_bajp_dicoding.ui.main.MainActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarTitle()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sectionPagerAdapter = AdapterSectionPagerFavorite(this, supportFragmentManager)
        binding.favoriteViewPager.adapter = sectionPagerAdapter
        binding.favoriteTabLayout.setupWithViewPager(binding.favoriteViewPager)

        supportActionBar?.elevation = 0f
    }

    private fun setActionBarTitle() {
        supportActionBar?.title = "Favorite Page"
    }
}