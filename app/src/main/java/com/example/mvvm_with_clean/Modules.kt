package com.example.mvvm_with_clean

import com.example.mvvm_with_clean.data.api.UserEndpoint
import com.example.mvvm_with_clean.data.di.NetworkServiceFactory
import com.example.mvvm_with_clean.data.repository.UserRepository
import com.example.mvvm_with_clean.presenter.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { NetworkServiceFactory() }

    factory { get<NetworkServiceFactory>().createNetworkService<UserEndpoint>() }

    factory { UserRepository(get()) }

    viewModel { UserViewModel(get()) }
}