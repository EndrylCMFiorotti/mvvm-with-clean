package com.example.mvvm_with_clean.fixture.presentation

import com.example.mvvm_with_clean.domain.presentation.UserPresentation

class UserListPresentationFixture(
    val name: String,
    val email: String,
    val age: String
) {
    companion object {
        fun getUserListComplete(
            withName: String = "endryl fiorotti",
            withEmail: String = "endryl@gmail.com",
            withAge: String = "20"
        ): UserListPresentationFixture {
            return UserListPresentationFixture(
                name = withName,
                email = withEmail,
                age = withAge
            )
        }
    }

    fun build() = listOf(
        UserPresentation(
            name = name,
            email = email,
            age = age
        )
    )
}