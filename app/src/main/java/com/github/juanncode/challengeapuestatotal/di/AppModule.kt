package com.github.juanncode.challengeapuestatotal.di


import android.content.Context
import com.github.juanncode.challengeapuestatotal.data.datasource.RemoteDataSource
import com.github.juanncode.challengeapuestatotal.data.datasource.RetrofitDataSource
import com.github.juanncode.challengeapuestatotal.data.repository.BetRepositoryImpl
import com.github.juanncode.challengeapuestatotal.domain.repository.BetRepository
import com.github.juanncode.challengeapuestatotal.retrofit.ApiService
import com.github.juanncode.challengeapuestatotal.retrofit.MockInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(mockInterceptor: MockInterceptor,
                            loggingInterceptor: HttpLoggingInterceptor

    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMockInterceptor(@ApplicationContext context: Context): MockInterceptor {
        return MockInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://localhost/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RetrofitDataSource) : RemoteDataSource


    @Binds
    abstract fun bindRepository(repository: BetRepositoryImpl) : BetRepository

}