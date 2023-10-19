package com.example.walmartcountryinfo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.walmartcountryinfo.databinding.FragmentCountryListBinding
import com.example.walmartcountryinfo.model.CountryResponse
import com.example.walmartcountryinfo.model.UIState

class CountryListFragment: ViewModelFragment() {
    private lateinit var binding: FragmentCountryListBinding
    private val countryAdapter by lazy {
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(layoutInflater)
        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.countryData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getCountries()
                }
                is UIState.Error -> {
                    binding.loadingInfo.visibility = View.GONE
                    binding.loadingText.text = uiState.error.message
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        loadingInfo.visibility = View.GONE
                        loadingText.visibility = View.GONE
                        countryAdapter.setCountryList((uiState.response as List<CountryResponse>))
                        browseCountryRecycler.adapter = countryAdapter
                    }
                }
            }
        }
    }
}