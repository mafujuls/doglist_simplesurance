package me.mafu.dogslist_simplesurance.di

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.mafu.dogslist_simplesurance.data.BreedsRepositoryImpl
import me.mafu.dogslist_simplesurance.data.local_data.LocalDataSource
import me.mafu.dogslist_simplesurance.data.local_data.LocalDataSourceImpl
import me.mafu.dogslist_simplesurance.data.local_data.db.BreedsDao
import me.mafu.dogslist_simplesurance.data.local_data.db.BreedsDatabase
import me.mafu.dogslist_simplesurance.data.remote_data.RemoteDataSource
import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository
import me.mafu.dogslist_simplesurance.domain.usecases.GetBreedsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideBreedsDatabase(@ApplicationContext app: Context): BreedsDatabase {
        return Room.databaseBuilder(app, BreedsDatabase::class.java, "breeds_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBreedsDao(breedsDatabase: BreedsDatabase): BreedsDao {
        return breedsDatabase.getBreedsDao()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: BreedsDao) : LocalDataSource {
        return LocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideGetBreedsUseCase(repository: BreedsRepository) : GetBreedsUseCase {
        return GetBreedsUseCase(repository)
    }

    /*@Singleton
    @Provides
    fun provideBreedsRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : BreedsRepository {
        return BreedsRepositoryImpl(remoteDataSource, localDataSource)
    }*/
}