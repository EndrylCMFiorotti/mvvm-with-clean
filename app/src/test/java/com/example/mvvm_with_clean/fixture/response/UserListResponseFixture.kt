package com.example.mvvm_with_clean.fixture.response

import com.example.mvvm_with_clean.data.response.UserResponse
import com.example.mvvm_with_clean.data.response.UsersResponse

class UserListResponseFixture(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val age: Int,
    val image: String,
    val emptyList: Boolean
) {
    companion object {
        fun getUserListComplete(
            withId: String = "1",
            withName: String = "endryl fiorotti",
            withEmail: String = "endryl@gmail.com",
            withPassword: String = "12345678",
            withAge: Int = 20,
            withImage: String = "image",
            withEmptyList: Boolean = false
        ): UserListResponseFixture {
            return UserListResponseFixture(
                id = withId,
                name = withName,
                email = withEmail,
                password = withPassword,
                age = withAge,
                image = withImage,
                emptyList = withEmptyList
            )
        }
    }

    fun build() = UsersResponse(
        userList = if (emptyList) emptyList() else listOf(
            UserResponse(
                id = id,
                name = name,
                age = age,
                email = email,
                password = password,
                image = image
            )
        )
    )
}