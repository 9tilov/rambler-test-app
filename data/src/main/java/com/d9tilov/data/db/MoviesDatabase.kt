package com.d9tilov.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.d9tilov.data.db.DATABASE.DATABASE_MOVIE_VERSION
import com.d9tilov.data.entities.MovieData

@Database(entities = [MovieData::class], version = DATABASE_MOVIE_VERSION, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}
