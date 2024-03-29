package com.yacine.listmovieapp.model.detailtvshow


import com.google.gson.annotations.SerializedName

data class NextEpisodeToAir(
    @SerializedName("air_date")
    var airDate: String,
    @SerializedName("episode_number")
    var episodeNumber: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("production_code")
    var productionCode: String,
    @SerializedName("season_number")
    var seasonNumber: Int,
    @SerializedName("show_id")
    var showId: Int,
    @SerializedName("still_path")
    var stillPath: Any,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
)