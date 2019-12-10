package com.yacine.listmovieapp.datasource.detailmovies

import com.yacine.listmovieapp.ext.*
import com.yacine.listmovieapp.model.detailtvshow.DetailTvShows
import com.yacine.listmovieapp.network.AppScheduler
import com.yacine.listmovieapp.network.ResultState
import com.yacine.listmovieapp.network.RetrofitApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


class DetailTVShowDataSource (
    private val api: RetrofitApi,
    private val scheduler: AppScheduler,
    private val compositeDisposable: CompositeDisposable) {

    val detailResult : PublishSubject<ResultState<DetailTvShows>> =
        PublishSubject.create<ResultState<DetailTvShows>>()

    fun fetchDetailTvShow(idTvShows : String){
        detailResult.loading(true)
        api.getDetailTvShow(idTvShows)
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