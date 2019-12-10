package com.yacine.listmovieapp.datasource.detailmovies

import com.yacine.listmovieapp.ext.*
import com.yacine.listmovieapp.model.detailmovie.DetailMovie
import com.yacine.listmovieapp.network.AppScheduler
import com.yacine.listmovieapp.network.ResultState
import com.yacine.listmovieapp.network.RetrofitApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


class DetailMovieDataSource (
    private val api: RetrofitApi,
    private val scheduler: AppScheduler,
    private val compositeDisposable: CompositeDisposable) {

    val detailResult : PublishSubject<ResultState<DetailMovie>> =
        PublishSubject.create<ResultState<DetailMovie>>()

    fun fetchDetailMovie(idMovie : String){
        detailResult.loading(true)
        api.getDetailMovie(idMovie)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    detailResult.success(it)
                },
                {
                    detailResult.failed(it)
                }
            ).addTo(compositeDisposable)
    }
}