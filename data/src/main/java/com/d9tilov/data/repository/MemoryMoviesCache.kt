package com.d9tilov.data.repository

import com.d9tilov.domain.MoviesCache
import com.d9tilov.domain.Optional
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

class MemoryMoviesCache : MoviesCache {

    private val movies: LinkedHashMap<Long, MovieEntity> = LinkedHashMap()

    override fun isEmpty(): Observable<Boolean> {
        return Observable.just(movies.isEmpty())
    }

    override fun remove(movieEntity: MovieEntity) {
        movies.remove(movieEntity.id)
    }

    override fun clear() {
        movies.clear()
    }

    override fun save(movieEntity: MovieEntity) {
        movies[movieEntity.id] = movieEntity
    }

    override fun saveAll(movieEntities: List<MovieEntity>) {
        movieEntities.forEach { movieEntity -> this.movies[movieEntity.id] = movieEntity }
    }

    override fun getAll(): Observable<List<MovieEntity>> {
        return Observable.just(movies.values.toList())
    }

    override fun get(movieId: Long): Observable<Optional<MovieEntity>> {
        return Observable.just(Optional.of(movies[movieId]))
    }
}

