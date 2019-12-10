package com.yacine.listmovieapp.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yacine.listmovieapp.base.Constants
import com.yacine.listmovieapp.datasource.tvshows.TvShowsDataSource
import com.yacine.listmovieapp.datasource.tvshows.TvShowsDataSourceFactory
import com.yacine.listmovieapp.model.tvshows.TvShow
import com.yacine.listmovieapp.network.PaginateResultState


class TvShowsRepository(private val factory: TvShowsDataSourceFactory){
    private lateinit var tvshowsPageList : LiveData<PagedList<TvShow>>

    fun fetchTvShows() : LiveData<PagedList<TvShow>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.ITEM_PER_PAGE)
            .build()
        tvshowsPageList = LivePagedListBuilder(factory,config).build()
        return tvshowsPageList
    }
    fun getFetchState() : LiveData<PaginateResultState>{
        return Transformations.switchMap<TvShowsDataSource,PaginateResultState>(
            factory.tvshowLiveDataSource,
            TvShowsDataSource::state
        )
    }
}