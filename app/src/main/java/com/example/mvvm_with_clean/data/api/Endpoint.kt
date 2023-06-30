package com.example.mvvm_with_clean.data.api

import com.example.mvvm_with_clean.data.response.UserResponse
import com.example.mvvm_with_clean.domain.user.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoint {
    @GET("users/user")
    suspend fun getUser(): List<UserResponse>

    @POST("users/user")
    suspend fun postUser(@Body user: UserDto)
}