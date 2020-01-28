package com.d9tilov.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieData(
    @SerializedName("id")
    @PrimaryKey
    var id: Long = 0,
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int = 0,
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("release_date")
    var releaseDate: String
)
        