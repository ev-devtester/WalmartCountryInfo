package com.example.walmartcountryinfo.data.api

import com.example.walmartcountryinfo.domain.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CountryRepository {
    suspend fun getCountries(): Flow<UIState>
}

class CountryRepositoryImpl(private val api: CountryApi): CountryRepository {
    override suspend fun getCountries(): Flow<UIState> =
        flow {
            try {
                val response = api.getCountries()
                if(response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty Response"))
                } else throw Exception("Failed Network Call")
            } catch (e: Exception) {
                emit(UIState.Error(Exception("There was an error that occurred!")))
            }
        }

}