package com.yacine.listmovieapp.datasource.tvshows

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yacine.listmovieapp.model.tvshows.TvShow
import com.yacine.listmovieapp.network.AppScheduler
import com.yacine.listmovieapp.network.RetrofitApi
import io.reactivex.disposables.CompositeDisposable



class TvShowsDataSourceFactory (
    private val api: RetrofitApi,
    private val scheduler: AppScheduler,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, TvShow>(){
    val tvshowLiveDataSource = MutableLiveData<TvShowsDataSource>()

    override fun create(): DataSource<Int, TvShow> {
        val tds = TvShowsDataSource(api,scheduler,compositeDisposable)
        tvshowLiveDataSource.postValue(tds)
        return tds
    }

}