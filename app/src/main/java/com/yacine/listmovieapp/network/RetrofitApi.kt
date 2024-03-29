package com.yacine.listmovieapp.network

import com.yacine.listmovieapp.BuildConfig
import com.yacine.listmovieapp.model.detailmovie.DetailMovie
import com.yacine.listmovieapp.model.detailtvshow.DetailTvShows
import com.yacine.listmovieapp.model.movies.Movies
import com.yacine.listmovieapp.model.tvshows.TvShows
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit



interface RetrofitApi {
    //TODO : api interface will written in here.

    @GET("/3/discover/movie")
    fun getMovies(@Query("page") page: Int):Single<Movies>

    @GET("/3/discover/tv")
    fun getTvShows(@Query("page") page: Int):Single<TvShows>

    @GET("/3/movie/{id_movie}")
    fun getDetailMovie(@Path("id_movie") idMovie: String):Single<DetailMovie>

    @GET("/3/tv/{id_tvshow}")
    fun getDetailTvShow(@Path("id_tvshow") idTvShow: String):Single<DetailTvShows>

    companion object{
        operator fun invoke() : RetrofitApi{
            val authInterceptor = Interceptor {chain->
                val newUrl = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key",BuildConfig.API_KEY)
                    .addQueryParameter("language","en-US")
                    .build()
                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitApi::class.java)
        }
    }
}