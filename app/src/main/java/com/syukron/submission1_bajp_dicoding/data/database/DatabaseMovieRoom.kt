package com.syukron.submission1_bajp_dicoding.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow

@Database(entities = [EntityMovie::class, EntityTvShow::class], version = 1, exportSchema = false)
abstract class DatabaseMovieRoom : RoomDatabase() {
    abstract fun movieDao(): DaoMovie

    companion object {
        @Volatile

        private var INSTANCE: DatabaseMovieRoom? = null

        @JvmStatic
        fun getInstance(context: Context): DatabaseMovieRoom =
            INSTANCE ?: synchronized(this)
            {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseMovieRoom::class.java,
                    "Movies.db"
                )
            }.build().apply {
                INSTANCE = this
            }
    }
}