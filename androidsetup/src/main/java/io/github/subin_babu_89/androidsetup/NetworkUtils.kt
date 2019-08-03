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

val okHttpClient : OkHttpClient = OkHttpClient.Builder().cache(null)
    .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.d("OkHttp logging intercept : %s",message)
        }
    })).connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).build()