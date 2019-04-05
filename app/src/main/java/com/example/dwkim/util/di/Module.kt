package com.example.dwkim.util.di

import com.example.dwkim.model.RepositoryImpl
import com.example.dwkim.repository.ENDPOINT
import com.example.dwkim.repository.IRepository
import com.example.dwkim.repository.NetworkRepository
import com.example.dwkim.util.rx.ApplicationSchedulerProvider
import com.example.dwkim.util.rx.SchedulerProvider
import com.example.dwkim.viewmodel.HomeViewModel
import com.example.dwkim.viewmodel.CardViewModel
import com.example.dwkim.viewmodel.UserViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module{
    single {
        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(
                OkHttpClient.Builder().apply{
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.HEADERS
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(NetworkRepository::class.java)
    }

    single {
        RepositoryImpl(androidContext(), get()) as IRepository
    }
}

val viewModelModules = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CardViewModel(get(), get()) }
    viewModel { UserViewModel(get(), get()) }
}

val rxModule = module {
    single<SchedulerProvider> { ApplicationSchedulerProvider() }
}

val appModules = listOf(apiModule, viewModelModules, rxModule)