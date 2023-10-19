package com.example.walmartcountryinfo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.walmartcountryinfo.databinding.FragmentStartingPageBinding

class StartingPageFragment: ViewModelFragment() {
    private lateinit var binding: FragmentStartingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartingPageBinding.inflate(layoutInflater)

        binding.startCountryBrowse.setOnClickListener {
            viewModel.setLoading()
            findNavController().navigate(
                StartingPageFragmentDirections
                    .actionStartToCountryView()
            )
        }

        return binding.root
    }
}