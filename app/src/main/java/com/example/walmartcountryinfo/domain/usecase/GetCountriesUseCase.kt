package com.example.walmartcountryinfo.domain.usecase

import com.example.walmartcountryinfo.data.api.CountryRepository
import com.example.walmartcountryinfo.domain.model.UIState
import kotlinx.coroutines.flow.Flow

class GetCountriesUseCase(val countriesRepository: CountryRepository) {

    suspend fun callApi() : Flow<UIState> {
        return countriesRepository.getCountries()
    }

}