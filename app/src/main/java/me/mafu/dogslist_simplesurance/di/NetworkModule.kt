package me.mafu.dogslist_simplesurance.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.mafu.dogslist_simplesurance.BuildConfig
import me.mafu.dogslist_simplesurance.data.remote_data.BreedsApiService
import me.mafu.dogslist_simplesurance.data.remote_data.RemoteDataSource
import me.mafu.dogslist_simplesurance.data.remote_data.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideBreedsApiService(retrofit: Retrofit) : BreedsApiService {
        return retrofit.create(BreedsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: BreedsApiService) : RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

}