package com.example.walmartcountryinfo.api

import com.example.walmartcountryinfo.model.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("countries.json")
    suspend fun getCountries(): Response<List<CountryResponse>>
}