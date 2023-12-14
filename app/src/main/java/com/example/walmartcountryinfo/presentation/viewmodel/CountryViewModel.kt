package com.example.walmartcountryinfo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartcountryinfo.domain.model.UIState
import com.example.walmartcountryinfo.domain.usecase.GetCountriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class CountryViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _countryData = MutableLiveData<UIState>()
    val countryData: LiveData<UIState> get() = _countryData

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("CountryViewModel",
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable)
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    fun getCountries() {
        viewModelSafeScope.launch(dispatcher) {
            getCountriesUseCase.callApi().collect{
                _countryData.postValue(it)
            }
        }
    }

    fun setLoading() { _countryData.value = UIState.Loading }
}