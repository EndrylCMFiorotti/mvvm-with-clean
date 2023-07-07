package com.example.mvvm_with_clean.data.response

import com.example.mvvm_with_clean.domain.presentation.UserPresentation

data class UsersResponse(
    val userList: List<UserResponse>
) {
    fun toUserListPresentation(): List<UserPresentation> = userList.map {
        UserPresentation(
            name = it.name,
            email = it.email,
            age = it.age.toString(),
            image = it.image
        )
    }
}