package com.yacine.listmovieapp.ui.detailmovie

import androidx.lifecycle.LiveData
import com.yacine.listmovieapp.datasource.detailmovies.DetailMovieDataSource
import com.yacine.listmovieapp.ext.toLiveData
import com.yacine.listmovieapp.model.detailmovie.DetailMovie
import com.yacine.listmovieapp.network.ResultState
import io.reactivex.disposables.CompositeDisposable


class DetailMovieRepository(private val dataSource : DetailMovieDataSource, private val compositeDisposable: CompositeDisposable) {

    val detailMovieResult: LiveData<ResultState<DetailMovie>> by lazy {
        dataSource.detailResult.toLiveData(compositeDisposable)
    }

    fun fetchDetail(idMovie : String) {
        dataSource.fetchDetailMovie(idMovie)
    }

    fun onCleared(){
        compositeDisposable.clear()
    }
}