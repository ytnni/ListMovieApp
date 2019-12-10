package com.yacine.listmovieapp.ui.detailtvshow

import androidx.lifecycle.LiveData
import com.yacine.listmovieapp.datasource.detailmovies.DetailMovieDataSource
import com.yacine.listmovieapp.datasource.detailmovies.DetailTVShowDataSource
import com.yacine.listmovieapp.ext.toLiveData
import com.yacine.listmovieapp.model.detailmovie.DetailMovie
import com.yacine.listmovieapp.model.detailtvshow.DetailTvShows
import com.yacine.listmovieapp.network.ResultState
import io.reactivex.disposables.CompositeDisposable


class DetailTvShowRepository(private val dataSource : DetailTVShowDataSource, private val compositeDisposable: CompositeDisposable) {

    val detailMovieResult: LiveData<ResultState<DetailTvShows>> by lazy {
        dataSource.detailResult.toLiveData(compositeDisposable)
    }

    fun fetchDetail(idTvShow : String) {
        dataSource.fetchDetailTvShow(idTvShow)
    }

    fun onCleared(){
        compositeDisposable.clear()
    }
}