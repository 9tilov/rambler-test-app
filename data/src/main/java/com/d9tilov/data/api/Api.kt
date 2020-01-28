package com.d9tilov.data.api

import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(): Observable<MovieListResult>

}
