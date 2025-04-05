package com.shawnie.catalog.di

import com.shawnie.catalog.domain.data.ItemsRepository
import com.shawnie.catalog.domain.data.ItemsRepositoryImpl
import com.shawnie.catalog.domain.remote.NetworkClient
import com.shawnie.catalog.domain.remote.NetworkClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemRemoteSource(): NetworkClient {
        return NetworkClientImpl(

        )
    }

    @Provides
    @Singleton
    fun provideItemRepository(networkClient: NetworkClient): ItemsRepository{
        return ItemsRepositoryImpl(networkClient)
    }
}