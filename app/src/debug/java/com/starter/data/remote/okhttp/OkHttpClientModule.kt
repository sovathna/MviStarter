package com.starter.data.remote.okhttp

import com.sovathna.androidmvi.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object OkHttpClientModule {

  @Provides
  @Singleton
  fun okHttpClient(
    logger: HttpLoggingInterceptor
  ): OkHttpClient =
    OkHttpClient()
      .newBuilder()
      .addInterceptor(logger)
      .build()

  @Provides
  @Singleton
  fun loggingInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor =
    HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)

  @Provides
  @Singleton
  fun logger(): HttpLoggingInterceptor.Logger =
    HttpLoggingInterceptor.Logger { message -> Logger.d(message) }

}