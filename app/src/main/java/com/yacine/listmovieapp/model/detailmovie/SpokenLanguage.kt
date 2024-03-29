package com.yacine.listmovieapp.model.detailmovie


import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    var iso6391: String,
    @SerializedName("name")
    var name: String
)