package com.example.mvvm_with_clean.presenter.viewModel

import com.example.mvvm_with_clean.data.network.wrapper.ResultWrapper
import com.example.mvvm_with_clean.data.repository.UserRepository
import com.example.mvvm_with_clean.domain.user.UserDto
import com.example.mvvm_with_clean.fixture.presentation.UserListPresentationFixture
import com.example.mvvm_with_clean.fixture.response.UserListResponseFixture
import com.example.mvvm_with_clean.presenter.util.CoroutineViewModelTest
import getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf


@ExperimentalCoroutinesApi
class UserViewModelTest : CoroutineViewModelTest() {
    private lateinit var repository: UserRepository
    private lateinit var viewModel: UserViewModel

    @Before
    override fun setUp() {
        repository = mockk(relaxed = true)
        viewModel = UserViewModel(repository)
    }

    @Test
    fun `WHEN successfully requested for a list of users, SHOULD insert into the liveData of the userList`() =
        runTest {
            coEvery { repository.getUser() } returns ResultWrapper.Success(
                UserListResponseFixture.getUserListComplete().build()
            )

            viewModel.getUser()

            val result = viewModel.userList.getOrAwaitValue()
            val expected = UserListPresentationFixture.getUserListComplete().build()

            assertEquals(expected, result)
        }

    @Test
    fun `WHEN successfully requested an empty user list, SHOULD enter empty liveData`() =
        runTest {
            coEvery { repository.getUser() } returns ResultWrapper.Success(
                UserListResponseFixture.getUserListComplete(withEmptyList = true).build()
            )

            viewModel.getUser()

            val result = viewModel.isEmpty.getOrAwaitValue()
            val expected = Unit

            assertEquals(expected, result)
        }

    @Test
    fun `WHEN executing the request with an error SHOULD show error request`() =
        runTest {
            coEvery { repository.getUser() } returns ResultWrapper.Error(Exception())

            viewModel.getUser()

            val result = viewModel.error.getOrAwaitValue()
            val expected = Exception()::class.java

            assertInstanceOf(expected, result)
        }

    @Test
    fun `WHEN sending an object and the response is successes, SHOULD be inserted in register livedata`() =
        runTest {
            coEvery { repository.postUser(USER) } returns ResultWrapper.Success(Unit)

            viewModel.postUser(USER)

            val result = viewModel.register.getOrAwaitValue()
            val expected = Unit

            assertEquals(expected, result)
        }

    @Test
    fun `WHEN sending an object and the response is fails, SHOULD be inserted in error livedata`() =
        runTest {
            coEvery { repository.postUser(USER) } returns ResultWrapper.Error(Exception())

            viewModel.postUser(USER)

            val result = viewModel.error.getOrAwaitValue()
            val expected = Exception()::class.java

            assertInstanceOf(expected, result)
        }

    companion object {
        private val USER = UserDto(
            name = "endryl fiorotti",
            email = "endryl@gmail.com",
            password = "12345678",
            age = 20
        )
    }
}