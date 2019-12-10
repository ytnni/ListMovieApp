package com.yacine.listmovieapp.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yacine.listmovieapp.base.Constants
import com.yacine.listmovieapp.datasource.movies.MoviesDataSource
import com.yacine.listmovieapp.datasource.movies.MoviesDataSourceFactory
import com.yacine.listmovieapp.model.movies.Movie
import com.yacine.listmovieapp.network.PaginateResultState


class MoviesRepository(private val factory: MoviesDataSourceFactory) {
    lateinit var moviesPagedList: LiveData<PagedList<Movie>>

    fun fetchMovies() : LiveData<PagedList<Movie>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.ITEM_PER_PAGE)
            .build()
        moviesPagedList = LivePagedListBuilder(factory,config).build()
        return moviesPagedList
    }

    fun getFetchState(): LiveData<PaginateResultState>{
        return Transformations.switchMap<MoviesDataSource,PaginateResultState>(
            factory.moviesLiveDataSource,
            MoviesDataSource::state
        )
    }
}