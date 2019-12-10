package com.yacine.listmovieapp.datasource.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.yacine.listmovieapp.base.Constants.Companion.FIRST_PAGE
import com.yacine.listmovieapp.ext.addTo
import com.yacine.listmovieapp.ext.performOnBackOutOnMain
import com.yacine.listmovieapp.model.movies.Movie
import com.yacine.listmovieapp.network.*
import io.reactivex.disposables.CompositeDisposable


class MoviesDataSource(
    private val api: RetrofitApi,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    val state : MutableLiveData<PaginateResultState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        state.postValue(PaginateResultState.LOADING)
        api.getMovies(FIRST_PAGE)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    state.postValue(PaginateResultState.LOADED)
                    callback.onResult(it.movies, null, FIRST_PAGE + 1)
                },
                {
                    state.postValue(PaginateResultState.ERROR)
                })
            .addTo(compositeDisposable)

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        state.postValue(PaginateResultState.LOADING)
        api.getMovies(params.key)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    if(it.totalPages >= params.key) {
                        callback.onResult(it.movies, params.key+1)
                        state.postValue(PaginateResultState.LOADED)
                    }
                    else{
                        state.postValue(PaginateResultState.EOF)
                    }
                },
                {
                    state.postValue(PaginateResultState.ERROR)
                }
            ).addTo(compositeDisposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}