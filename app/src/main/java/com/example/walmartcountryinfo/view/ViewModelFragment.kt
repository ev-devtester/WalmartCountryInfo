package com.example.walmartcountryinfo.view

import androidx.fragment.app.Fragment
import com.example.walmartcountryinfo.di.DI

open class ViewModelFragment: Fragment() {

    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity())
    }
}