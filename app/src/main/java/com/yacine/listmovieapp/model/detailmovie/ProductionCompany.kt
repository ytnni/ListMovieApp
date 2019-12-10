package com.yacine.listmovieapp.model.detailmovie


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    var id: Int,
    @SerializedName("logo_path")
    var logoPath: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("origin_country")
    var originCountry: String
)