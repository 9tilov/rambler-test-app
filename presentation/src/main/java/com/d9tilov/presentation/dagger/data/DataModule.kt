package com.d9tilov.presentation.dagger.data

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.d9tilov.data.api.Api
import com.d9tilov.data.db.MoviesDatabase
import com.d9tilov.data.db.RoomFavoritesMovieCache
import com.d9tilov.data.mappers.DetailsDataMovieEntityMapper
import com.d9tilov.data.mappers.MovieDataEntityMapper
import com.d9tilov.data.mappers.MovieEntityDataMapper
import com.d9tilov.data.repository.*
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

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: Api,
        @Named(DI.favoritesCache) cache: MoviesCache,
        sharedPreferences: MovieSharedPreferences,
        movieDataMapper: MovieDataEntityMapper,
        detailedDataMapper: DetailsDataMovieEntityMapper
    ): MoviesRepository {
        return MoviesRepositoryImpl(api, cache, sharedPreferences, movieDataMapper, detailedDataMapper)
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
        movieDataMapper: MovieDataEntityMapper,
        detailedDataMapper: DetailsDataMovieEntityMapper
    ): MoviesDataStore {
        return RemoteMoviesDataStore(api, movieDataMapper, detailedDataMapper)
    }

    @Singleton
    @Provides
    @Named(DI.cachedDataStore)
    fun provideCachedMoviesDataStore(moviesCache: MoviesCache): MoviesDataStore {
        return CachedMoviesDataStore(moviesCache)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context):
            SharedPreferences {
        return context.getSharedPreferences("storage", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideCurrencyPreferences(sharedPreferences: SharedPreferences):
            MovieSharedPreferences {
        return MovieSharedPreferences(sharedPreferences)
    }
}
