package com.yacine.listmovieapp.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yacine.listmovieapp.model.movies.Movie
import com.yacine.listmovieapp.network.PaginateResultState
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel(private val repository: MoviesRepository,
                      private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val movies : LiveData<PagedList<Movie>> by lazy {
        repository.fetchMovies()
    }

    val resultState : LiveData<PaginateResultState> by lazy {
        repository.getFetchState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}