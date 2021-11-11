package com.syukron.submission1_bajp_dicoding.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.syukron.submission1_bajp_dicoding.data.MovieResponse
import com.syukron.submission1_bajp_dicoding.data.TvShowResponse
import com.syukron.submission1_bajp_dicoding.data.entity.EntityMovie
import com.syukron.submission1_bajp_dicoding.data.entity.EntityTvShow
import com.syukron.submission1_bajp_dicoding.data.source.remote.ApiResponse
import com.syukron.submission1_bajp_dicoding.data.source.response.RemoteDataSource
import com.syukron.submission1_bajp_dicoding.utils.AppExecutors
import com.syukron.submission1_bajp_dicoding.vo.Resource

class DataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {
    override fun getMovie(): LiveData<Resource<PagedList<EntityMovie>>> {
        return object :
            NetworkBoundResource<PagedList<EntityMovie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<EntityMovie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllPopularMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<EntityMovie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<EntityMovie>()
                for (response in data) {
                    val movie = EntityMovie(
                        response.title,
                        response.releaseDate,
                        response.score,
                        response.description,
                        response.imagePath,
                        false,
                        response.id
                    )
                    movieList.add(movie)
                }
                localDataSource.insertPopularMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<EntityMovie> {
        return localDataSource.getMovieDetail(id)
    }

    override fun getTVShow(): LiveData<Resource<PagedList<EntityTvShow>>> {
        return object :
            NetworkBoundResource<PagedList<EntityTvShow>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<EntityTvShow>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllPopularTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<EntityTvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTVShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<EntityTvShow>()
                for (response in data) {
                    val tvSerial = EntityTvShow(
                        response.title,
                        response.releaseDate,
                        response.score,
                        response.description,
                        response.imagePath,
                        false,
                        response.id
                    )
                    tvShowList.add(tvSerial)
                }
                localDataSource.insertPopularTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getDetailTVShow(id: String): LiveData<EntityTvShow> {
        return localDataSource.getTvShowDetail(id)
    }

    override fun setMovieFavorite(movieFavorited: EntityMovie, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieBookmark(movieFavorited, state) }

    override fun getFavoritedMovies(): LiveData<PagedList<EntityMovie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }

    override fun setTvShowFavorite(tvShowFavorited: EntityTvShow, state: Boolean) =
        appExecutors.diskIO()
            .execute { localDataSource.setTvSerialBookmark(tvShowFavorited, state) }

    override fun getFavoritedTvShow(): LiveData<PagedList<EntityTvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShows(), config).build()
    }

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }
}
