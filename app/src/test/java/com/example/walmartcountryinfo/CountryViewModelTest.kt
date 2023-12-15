package com.example.walmartcountryinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.walmartcountryinfo.domain.model.UIState
import com.example.walmartcountryinfo.domain.usecase.GetCountriesUseCase
import com.example.walmartcountryinfo.presentation.viewmodel.CountryViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountryViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @MockK
    lateinit var getCountriesUseCase: GetCountriesUseCase
    private lateinit var viewModel: CountryViewModel
    private val dispatcher = Dispatchers.Unconfined

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CountryViewModel(getCountriesUseCase, dispatcher)
    }

    @Test
    fun `use case should return success state`() {
        val state = UIState.Success(Any())

        coEvery {
            getCountriesUseCase.callApi()
        } returns flow {
            emit(state)
        }

        viewModel.getCountries()
        assertEquals(viewModel.countryData.value, state)
    }

    @Test
    fun `use case should return error state`() {
        val state = UIState.Error(Exception(""))

        coEvery {
            getCountriesUseCase.callApi()
        } returns flow {
            emit(state)
        }

        viewModel.getCountries()
        assertEquals(viewModel.countryData.value, state)
    }

}