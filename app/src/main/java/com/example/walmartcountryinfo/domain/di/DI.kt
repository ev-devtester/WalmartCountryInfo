package com.example.walmartcountryinfo.domain.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.walmartcountryinfo.data.api.CountryApi
import com.example.walmartcountryinfo.data.api.CountryRepositoryImpl
import com.example.walmartcountryinfo.data.util.BASE_URL
import com.example.walmartcountryinfo.domain.usecase.GetCountriesUseCase
import com.example.walmartcountryinfo.presentation.viewmodel.CountryViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DI {
    private val service =  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpClient())
        .build()
        .create(CountryApi::class.java)

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRepository() = CountryRepositoryImpl(service)
    private fun provideDispatcher() = Dispatchers.IO
    private fun provideUseCase() = GetCountriesUseCase(provideRepository())

    fun provideViewModel(storeOwner: ViewModelStoreOwner): CountryViewModel {
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CountryViewModel(provideUseCase(), provideDispatcher()) as T
            }
        })[CountryViewModel::class.java]
    }
}