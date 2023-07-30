package me.mafu.dogslist_simplesurance.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.mafu.dogslist_simplesurance.data.BreedsRepositoryImpl
import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindDataRepository(repository: BreedsRepositoryImpl): BreedsRepository
}