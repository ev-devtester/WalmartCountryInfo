package com.example.walmartcountryinfo.presentation.view

import androidx.fragment.app.Fragment
import com.example.walmartcountryinfo.domain.di.DI

open class ViewModelFragment: Fragment() {

    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity())
    }
}