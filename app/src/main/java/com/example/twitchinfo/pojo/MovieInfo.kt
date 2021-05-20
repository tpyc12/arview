package com.example.twitchinfo.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.twitchinfo.api.ApiFactory.BASE_IMAGE_URL
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieInfo(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("popularity")
    @Expose
    val popularity: Double,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,

    ) {
    fun getFullImageUrl(posterSize: String): String {
        return BASE_IMAGE_URL + posterSize + posterPath
    }
}
