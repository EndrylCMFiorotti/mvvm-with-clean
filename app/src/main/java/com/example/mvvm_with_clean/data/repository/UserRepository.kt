package com.example.mvvm_with_clean.data.repository

import com.example.mvvm_with_clean.data.api.Endpoint
import com.example.mvvm_with_clean.data.network.handler.requestHandler
import com.example.mvvm_with_clean.data.network.wrapper.ResultWrapper
import com.example.mvvm_with_clean.data.response.UserResponse
import com.example.mvvm_with_clean.domain.user.UserDto

class UserRepository(private val endpoint: Endpoint) {
    suspend fun getUser(): ResultWrapper<List<UserResponse>> {
        return requestHandler {
            endpoint.getUser()
        }
    }

    suspend fun postUser(user: UserDto): ResultWrapper<Unit> {
        return requestHandler {
            endpoint.postUser(user)
        }
    }
}