package com.example.mvvm_with_clean.data.repository

import com.example.mvvm_with_clean.data.api.UserEndpoint
import com.example.mvvm_with_clean.data.ext.toResponse
import com.example.mvvm_with_clean.data.network.handler.requestHandler
import com.example.mvvm_with_clean.data.network.wrapper.ResultWrapper
import com.example.mvvm_with_clean.data.response.UsersResponse
import com.example.mvvm_with_clean.domain.user.UserDto

class UserRepository(private val userEndpoint: UserEndpoint) {
    suspend fun getUser(): ResultWrapper<UsersResponse> {
        return requestHandler {
            userEndpoint.getUser().toResponse()
        }
    }

    suspend fun postUser(user: UserDto): ResultWrapper<Unit> {
        return requestHandler {
            userEndpoint.postUser(user)
        }
    }
}