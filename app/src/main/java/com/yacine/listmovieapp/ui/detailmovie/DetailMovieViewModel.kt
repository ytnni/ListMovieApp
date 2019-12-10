package com.yacine.listmovieapp.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yacine.listmovieapp.model.detailmovie.DetailMovie
import com.yacine.listmovieapp.network.ResultState
import io.reactivex.disposables.CompositeDisposable


class DetailMovieViewModel(private val movieRepository: DetailMovieRepository) : ViewModel() {

    val detailMovie : LiveData<ResultState<DetailMovie>> by lazy {
        movieRepository.detailMovieResult
    }

    fun loadDetailMovie(idMovie : String){
        movieRepository.fetchDetail(idMovie)
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.onCleared()
    }
}