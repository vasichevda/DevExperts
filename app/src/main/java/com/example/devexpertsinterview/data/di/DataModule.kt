package com.example.devexpertsinterview.data.di

import com.example.devexpertsinterview.data.repository.DadJokesRepositoryImpl
import com.example.devexpertsinterview.data.api.DadJokesApiService
import com.example.devexpertsinterview.domain.repository.DadJokesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://icanhazdadjoke.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): DadJokesApiService =
        retrofit.create(DadJokesApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: DadJokesApiService): DadJokesRepository =
        DadJokesRepositoryImpl(api)
}
