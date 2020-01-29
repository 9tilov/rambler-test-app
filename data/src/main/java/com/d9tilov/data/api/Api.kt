package com.d9tilov.data.api

import com.d9tilov.data.entities.DetailsData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("movie/{id}?append_to_response=videos,reviews")
    fun getMovieDetails(@Path("id") movieId: Long): Observable<DetailsData>

    @GET("movie/popular")
    fun popularMovies(): Observable<MovieListResult>

}
