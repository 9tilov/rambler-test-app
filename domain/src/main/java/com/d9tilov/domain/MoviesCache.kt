package com.d9tilov.domain

import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

interface MoviesCache {

    fun clear()
    fun save(movieEntity: MovieEntity)
    fun remove(movieEntity: MovieEntity)
    fun saveAll(movieEntities: List<MovieEntity>)
    fun getAll(): Observable<List<MovieEntity>>
    fun get(movieId: Long): Observable<Optional<MovieEntity>>
    fun isEmpty(): Observable<Boolean>
}
