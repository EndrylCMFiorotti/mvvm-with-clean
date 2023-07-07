package com.example.mvvm_with_clean.data.ext

import com.example.mvvm_with_clean.data.data.UserData
import com.example.mvvm_with_clean.data.response.UserResponse
import com.example.mvvm_with_clean.data.response.UsersResponse

fun List<UserData>.toResponse() = UsersResponse(
    userList = this.map {
        it.toResponse()
    }
)

private fun UserData.toResponse() = UserResponse(
    id = id,
    name = name,
    age = age,
    email = email,
    password = password,
    image = image
)