package com.example.walmartcountryinfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartcountryinfo.api.CountryRepository
import com.example.walmartcountryinfo.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class CountryViewModel(
    private val repository: CountryRepository,
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
            repository.getCountries().collect{
                _countryData.postValue(it)
            }
        }
    }

    fun setLoading() { _countryData.value = UIState.Loading }
}