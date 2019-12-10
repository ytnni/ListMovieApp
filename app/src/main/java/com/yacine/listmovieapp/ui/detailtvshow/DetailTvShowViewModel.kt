package com.yacine.listmovieapp.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yacine.listmovieapp.model.detailtvshow.DetailTvShows
import com.yacine.listmovieapp.network.ResultState


class DetailTvShowViewModel(private val tvShowRepository: DetailTvShowRepository) : ViewModel() {

    val detailTvShows : LiveData<ResultState<DetailTvShows>> by lazy {
        tvShowRepository.detailMovieResult
    }

    fun loadDetailMovie(idTvShows : String){
        tvShowRepository.fetchDetail(idTvShows)
    }

    override fun onCleared() {
        super.onCleared()
        tvShowRepository.onCleared()
    }
}