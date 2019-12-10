package com.yacine.listmovieapp.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yacine.listmovieapp.model.tvshows.TvShow
import com.yacine.listmovieapp.network.PaginateResultState
import io.reactivex.disposables.CompositeDisposable

class TvShowsViewModel(private val repository: TvShowsRepository,
                       private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val tvshows : LiveData<PagedList<TvShow>> by lazy {
        repository.fetchTvShows()
    }
    val resultState : LiveData<PaginateResultState> by lazy {
        repository.getFetchState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}