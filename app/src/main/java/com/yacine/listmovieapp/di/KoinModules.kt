package com.yacine.listmovieapp.di

import com.yacine.listmovieapp.datasource.detailmovies.DetailMovieDataSource
import com.yacine.listmovieapp.datasource.detailmovies.DetailTVShowDataSource
import com.yacine.listmovieapp.datasource.movies.MoviesDataSourceFactory
import com.yacine.listmovieapp.datasource.tvshows.TvShowsDataSourceFactory
import com.yacine.listmovieapp.di.ScopeNames.DetailMovieScopes
import com.yacine.listmovieapp.di.ScopeNames.DetailTvShowScopes
import com.yacine.listmovieapp.di.ScopeNames.MoviesListScopes
import com.yacine.listmovieapp.di.ScopeNames.TvShowsListScopes
import com.yacine.listmovieapp.network.AppScheduler
import com.yacine.listmovieapp.network.RetrofitApi
import com.yacine.listmovieapp.ui.detailmovie.DetailMovieRepository
import com.yacine.listmovieapp.ui.detailmovie.DetailMovieViewModel
import com.yacine.listmovieapp.ui.detailtvshow.DetailTvShowRepository
import com.yacine.listmovieapp.ui.detailtvshow.DetailTvShowViewModel
import com.yacine.listmovieapp.ui.main.movie.MoviesRepository
import com.yacine.listmovieapp.ui.main.movie.MoviesViewModel
import com.yacine.listmovieapp.ui.main.tvshow.TvShowsRepository
import com.yacine.listmovieapp.ui.main.tvshow.TvShowsViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module



object ScopeNames {
    const val MoviesListScopes = "MoviesFragment"
    const val TvShowsListScopes = "TvShowsFragment"
    const val DetailMovieScopes = "DetailMovieActivity"
    const val DetailTvShowScopes = "DetailTvShowActivity"
}

val networkModule = module {
    single { RetrofitApi() }
    single { AppScheduler() }
}

val scopesModule = module {
    scope(named(MoviesListScopes)) {
        scoped { CompositeDisposable() }
        scoped { MoviesRepository(get()) }
        scoped {
            MoviesDataSourceFactory(
                get(),
                get(),
                get()
            )
        }
        viewModel { MoviesViewModel(get(), get()) }
    }
    scope(named(TvShowsListScopes)) {
        scoped { CompositeDisposable() }
        scoped { TvShowsRepository(get()) }
        scoped {
            TvShowsDataSourceFactory(
                get(),
                get(),
                get()
            )
        }
        viewModel { TvShowsViewModel(get(), get()) }
    }

    scope(named(DetailMovieScopes)) {
        scoped { CompositeDisposable() }
        scoped { DetailMovieRepository(get(), get()) }
        scoped {
            DetailMovieDataSource(
                get(),
                get(),
                get()
            )
        }
        viewModel { DetailMovieViewModel(get()) }
    }
    scope(named(DetailTvShowScopes)) {
        scoped { CompositeDisposable() }
        scoped { DetailTvShowRepository(get(), get()) }
        scoped {
            DetailTVShowDataSource(
                get(),
                get(),
                get()
            )
        }
        viewModel { DetailTvShowViewModel(get()) }
    }
}
