package dev.josepatino.liftheavy.di

import androidx.annotation.RequiresApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.josepatino.liftheavy.api.ExerciseDbApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExerciseDbModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): ExerciseDbApi {
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create()
            ).baseUrl(ExerciseDbApi.ExerciseDbApiBaseUrl)
            .client(okHttpClient).build().create(ExerciseDbApi::class.java)
    }
}