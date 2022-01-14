package com.app.currencyconverter.di.modules

import android.app.Application
import com.app.currencyconverter.data.remote.ApiService
import com.app.currencyconverter.data.repository.Repository
import com.app.currencyconverter.data.repository.RepositoryImpl
import com.app.currencyconverter.utils.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideRepository(stringUtils: StringUtils, apiService: ApiService): Repository {
        return RepositoryImpl(stringUtils, apiService)
    }
}
