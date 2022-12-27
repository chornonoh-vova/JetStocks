package com.jetstocks.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesCache(@ApplicationContext context: Context) = Cache(
        File(context.cacheDir, "jetstocks_cache"),
        (20 * 1024 * 1024).toLong()
    )

    @Provides
    fun providesLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    @Provides
    fun providesOkHttpClient(cache: Cache, loggingInterceptor: Interceptor) = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(loggingInterceptor)
        .build()
}