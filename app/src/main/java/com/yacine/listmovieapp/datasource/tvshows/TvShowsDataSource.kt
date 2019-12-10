package com.yacine.listmovieapp.datasource.tvshows

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.yacine.listmovieapp.base.Constants.Companion.FIRST_PAGE
import com.yacine.listmovieapp.ext.addTo
import com.yacine.listmovieapp.ext.performOnBackOutOnMain
import com.yacine.listmovieapp.model.tvshows.TvShow
import com.yacine.listmovieapp.network.PaginateResultState
import com.yacine.listmovieapp.network.RetrofitApi
import com.yacine.listmovieapp.network.Scheduler
import io.reactivex.disposables.CompositeDisposable



class TvShowsDataSource(
    private val api: RetrofitApi,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, TvShow>() {

    val state: MutableLiveData<PaginateResultState> = MutableLiveData()


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        state.postValue(PaginateResultState.LOADING)
        api.getTvShows(FIRST_PAGE)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    state.postValue(PaginateResultState.LOADED)
                    callback.onResult(it.results, null, FIRST_PAGE + 1)
                },
                {
                    state.postValue(PaginateResultState.ERROR)
                }
            ).addTo(compositeDisposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        state.postValue(PaginateResultState.LOADING)
        api.getTvShows(params.key)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    if (it.totalPages >= params.key) {
                        callback.onResult(it.results, params.key + 1)
                        state.postValue(PaginateResultState.LOADED)
                    } else {
                        state.postValue(PaginateResultState.EOF)
                    }
                },
                {
                    state.postValue(PaginateResultState.ERROR)
                }
            ).addTo(compositeDisposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}