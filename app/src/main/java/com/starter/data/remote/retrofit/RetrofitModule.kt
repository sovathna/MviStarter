package com.starter.data.remote.retrofit

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

  private const val KEY_BASE_URL = "base_url"

  @Provides
  @Singleton
  @Named(KEY_BASE_URL)
  fun baseUrl() = "https://example.com"

  @Provides
  @Singleton
  fun callAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.createAsync()

  @Provides
  @Singleton
  fun converterFactory(moshi: Moshi): Converter.Factory =
    MoshiConverterFactory.create(moshi).withNullSerialization()

  @Provides
  @Singleton
  fun moshi(): Moshi = Moshi.Builder().build()

  @Provides
  @Singleton
  fun retrofit(
    @Named(KEY_BASE_URL)
    baseUrl: String,
    client: OkHttpClient,
    callAdapterFactory: CallAdapter.Factory,
    converterFactory: Converter.Factory
  ): Retrofit =
    Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(client)
      .addCallAdapterFactory(callAdapterFactory)
      .addConverterFactory(converterFactory)
      .build()

}