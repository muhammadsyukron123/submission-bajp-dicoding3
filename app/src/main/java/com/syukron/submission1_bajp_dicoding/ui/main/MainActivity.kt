package com.syukron.submission1_bajp_dicoding.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.databinding.ActivityMainBinding

import com.syukron.submission1_bajp_dicoding.ui.favorite.FavoriteActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val sectionPagerAdapter = AdapterSectionPager(this, supportFragmentManager)
        activityMainBinding.homeViewPager.adapter = sectionPagerAdapter
        activityMainBinding.homeTabLayout.setupWithViewPager(activityMainBinding.homeViewPager)

        supportActionBar?.elevation = 0f

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite) {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}