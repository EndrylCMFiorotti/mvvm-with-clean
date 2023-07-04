package com.example.mvvm_with_clean.data.api

import com.example.mvvm_with_clean.data.data.UserData
import com.example.mvvm_with_clean.domain.user.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserEndpoint {
    @GET("users/user")
    suspend fun getUser(): List<UserData>

    @POST("users/user")
    suspend fun postUser(@Body user: UserDto)
}