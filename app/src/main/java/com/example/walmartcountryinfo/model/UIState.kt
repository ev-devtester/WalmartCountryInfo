package com.example.walmartcountryinfo.model

sealed class UIState  {
    object Loading: UIState()
    class Error(val error: Exception): UIState()
    class Success<T>(val response: T): UIState()
}
