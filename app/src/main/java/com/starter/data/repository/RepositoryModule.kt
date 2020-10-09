package com.starter.data.repository

import com.starter.data.repository.base.Repository
import com.starter.data.repository.impl.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

  @Binds
  @Singleton
  abstract fun repository(impl: RepositoryImpl): Repository

}