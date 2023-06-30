package com.example.mvvm_with_clean

import com.example.mvvm_with_clean.data.di.RetrofitObject
import com.example.mvvm_with_clean.data.repository.UserRepository
import com.example.mvvm_with_clean.presenter.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    factory {
        UserRepository(RetrofitObject.createNetworkService())
    }
    viewModel {
        UserViewModel(get())
    }
}