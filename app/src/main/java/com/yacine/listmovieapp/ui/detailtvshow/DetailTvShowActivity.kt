package com.yacine.listmovieapp.ui.detailtvshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import coil.api.load
import com.yacine.listmovieapp.BuildConfig
import com.yacine.listmovieapp.R
import com.yacine.listmovieapp.di.ScopeNames
import com.yacine.listmovieapp.model.detailtvshow.DetailTvShows
import com.yacine.listmovieapp.model.tvshows.TvShow
import com.yacine.listmovieapp.network.ResultState
import com.yacine.listmovieapp.utils.genre.GenreGenerator
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.content_detail_tv_show.*
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class DetailTvShowActivity : AppCompatActivity() {
    companion object {
        const val ARG_TVSHOW_PARCELABLE = "TVSHOW"
        fun run(context: Context?, tvShow: TvShow?) {
            val intent = Intent(context, DetailTvShowActivity::class.java)
            intent.putExtra(ARG_TVSHOW_PARCELABLE, tvShow)
            context?.startActivity(intent)
        }
    }

    private val scopeId = "detailTvShowsScope"
    private val scopeName = getKoin().createScope(scopeId, named(ScopeNames.DetailTvShowScopes))
    private val detailTvShowViewModel: DetailTvShowViewModel by scopeName.viewModel(this)
    private lateinit var tvShow: TvShow
    private lateinit var detailTvShow: DetailTvShows
    private val TAG = DetailTvShowActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        setSupportActionBar(toolbar)
        toolbar_layout.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getIntentData()
        detailTvShowViewModel.loadDetailMovie(tvShow.id)
        detailTvShowViewModel.detailTvShows.observe(this) {
            when (it) {
                is ResultState.Progress -> {
                    d(TAG, "On progress")
                }
                is ResultState.Success -> {
                    d(TAG, it.data.originalName)
                    this.detailTvShow = it.data
                    setDetailData()
                }
                is ResultState.Failure -> {
                    d(TAG, "Error")
                }
            }
        }
    }

    private fun setDetailData() {
        toolbar_layout.title = detailTvShow.name
        supportActionBar?.subtitle = detailTvShow.status
        txt_title_detail.text = detailTvShow.name
        txt_detail_content.text = detailTvShow.overview
        txt_genre_content.text = GenreGenerator.getAllGenre(detailTvShow.genres)
        rating_view.rating = (detailTvShow.voteAverage.div(2)).toFloat()
        txt_rating_total.text = detailTvShow.voteAverage.toString()
        img_banner_movie.load(BuildConfig.BASE_POSTER_IMG_URL + detailTvShow.backdropPath)
        img_poster_detail.load(BuildConfig.BASE_POSTER_IMG_URL + detailTvShow.posterPath)    }

    private fun getIntentData() {
        tvShow = intent.getParcelableExtra(ARG_TVSHOW_PARCELABLE)
    }
    override fun onDestroy() {
        super.onDestroy()
        scopeName.close()
    }
}
