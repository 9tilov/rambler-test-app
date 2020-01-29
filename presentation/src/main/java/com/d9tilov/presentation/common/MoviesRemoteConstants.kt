package com.d9tilov.presentation.common

import com.d9tilov.presentation.BuildConfig

object Endpoint {
    const val MOVIE_URL = " http://api.themoviedb.org/3/"
}

object Query {
    const val API_KEY = "api_key"
    const val PAGE = "page"
    const val SORT_BY = "sort_by"
    const val LANGUAGE = "language"
    const val INCLUDE_ADULT = "include_adult"

    const val SORT_BY_DEFAULT = "popularity.desc"
    const val LANGUAGE_DEFAULT = "en-US"
    const val INCLUDE_ADULT_DEFAULT = false

    const val API_KEY_VALUE = BuildConfig.MOVIE_API_KEY
}
