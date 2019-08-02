package io.github.subin_babu_89.androidsetup

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Util class for all network related util methods
 *
 * @author sbabu
 */


val okHttpClient = OkHttpClient.Builder().cache(null)
    .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        Timber.d(
            "okhttp client intercept : %s",
            message
        )
    })).connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).build()