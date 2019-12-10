package com.yacine.listmovieapp.datasource.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yacine.listmovieapp.datasource.movies.MoviesDataSource
import com.yacine.listmovieapp.model.movies.Movie
import com.yacine.listmovieapp.network.AppScheduler
import com.yacine.listmovieapp.network.RetrofitApi
import io.reactivex.disposables.CompositeDisposable



class MoviesDataSourceFactory(
    private val api: RetrofitApi,
    private val scheduler: AppScheduler,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource = MutableLiveData<MoviesDataSource>()
    override fun create(): DataSource<Int, Movie> {
        val mds = MoviesDataSource(
            api,
            scheduler,
            compositeDisposable
        )
        moviesLiveDataSource.postValue(mds)
        return mds
    }

}