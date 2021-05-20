package com.example.twitchinfo.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieListOfResult(
    @SerializedName("results")
    @Expose
    val results: List<MovieInfo>
)