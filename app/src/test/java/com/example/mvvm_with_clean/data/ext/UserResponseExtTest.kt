package com.example.mvvm_with_clean.data.ext

import com.example.mvvm_with_clean.data.data.UserData
import com.example.mvvm_with_clean.fixture.response.UserListResponseFixture
import com.example.mvvm_with_clean.util.CoroutineViewModelTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class UserResponseExtTest : CoroutineViewModelTest() {
    @Test
    fun `WHEN converting UserData list to UsersResponse, SHOULD return valid response with mapped data`() {
        val userDataList = listOf(userData)

        val result = userDataList.toResponse()
        val expected = usersResponse

        assertEquals(expected, result)

        assertEquals(result.userList.first(), usersResponse.userList.first())
        assertEquals(result.userList.first().id, usersResponse.userList.first().id)
        assertEquals(result.userList.first().name, usersResponse.userList.first().name)
        assertEquals(result.userList.first().email, usersResponse.userList.first().email)
        assertEquals(result.userList.first().password, usersResponse.userList.first().password)
        assertEquals(result.userList.first().age, usersResponse.userList.first().age)
    }

    companion object {
        private val userData = UserData(
            id ="1",
            name = "endryl fiorotti",
            age = 20,
            email = "endryl@gmail.com",
            password = "12345678"
        )

        private val usersResponse = UserListResponseFixture.getUserListComplete().build()
    }
}