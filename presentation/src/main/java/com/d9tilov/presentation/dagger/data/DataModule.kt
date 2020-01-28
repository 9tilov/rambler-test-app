package com.d9tilov.presentation.dagger.data

import android.content.Context
import androidx.room.Room
import com.d9tilov.data.api.Api
import com.d9tilov.data.db.MoviesDatabase
import com.d9tilov.data.db.RoomFavoritesMovieCache
import com.d9tilov.data.mappers.MovieDataEntityMapper
import com.d9tilov.data.mappers.MovieEntityDataMapper
import com.d9tilov.data.repository.CachedMoviesDataStore
import com.d9tilov.data.repository.MemoryMoviesCache
import com.d9tilov.data.repository.MoviesRepositoryImpl
import com.d9tilov.data.repository.RemoteMoviesDataStore
import com.d9tilov.domain.MoviesCache
import com.d9tilov.domain.MoviesDataStore
import com.d9tilov.domain.MoviesRepository
import com.d9tilov.presentation.dagger.DI
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: Api,
        @Named(DI.favoritesCache) cache: MoviesCache,
        movieDataMapper: MovieDataEntityMapper
    ): MoviesRepository {

        return MoviesRepositoryImpl(api, cache, movieDataMapper)
    }

    @Singleton
    @Provides
    @Named(DI.inMemoryCache)
    fun provideInMemoryMoviesCache(): MoviesCache {
        return MemoryMoviesCache()
    }

    @Singleton
    @Provides
    @Named(DI.favoritesCache)
    fun provideFavoriteMoviesCache(
        moviesDatabase: MoviesDatabase,
        entityDataMapper: MovieEntityDataMapper,
        dataEntityMapper: MovieDataEntityMapper
    ): MoviesCache {
        return RoomFavoritesMovieCache(moviesDatabase, entityDataMapper, dataEntityMapper)
    }

    @Singleton
    @Provides
    @Named(DI.remoteDataStore)
    fun provideRemoteMovieDataStore(
        api: Api,
        movieDataMapper: MovieDataEntityMapper
    ): MoviesDataStore {
        return RemoteMoviesDataStore(api, movieDataMapper)
    }

    @Singleton
    @Provides
    @Named(DI.cachedDataStore)
    fun provideCachedMoviesDataStore(moviesCache: MoviesCache): MoviesDataStore {
        return CachedMoviesDataStore(moviesCache)
    }
}
