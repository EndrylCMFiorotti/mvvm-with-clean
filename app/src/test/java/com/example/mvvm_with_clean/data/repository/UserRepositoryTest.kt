package com.example.mvvm_with_clean.data.repository

import com.example.mvvm_with_clean.data.api.UserEndpoint
import com.example.mvvm_with_clean.data.network.exception.ClientException
import com.example.mvvm_with_clean.data.network.exception.ServerException
import com.example.mvvm_with_clean.data.network.exception.UnknownResponseException
import com.example.mvvm_with_clean.data.network.wrapper.ResultWrapper
import com.example.mvvm_with_clean.domain.user.UserDto
import com.example.mvvm_with_clean.util.CoroutineViewModelTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class UserRepositoryTest : CoroutineViewModelTest() {
    private lateinit var endpoint: UserEndpoint
    private lateinit var repository: UserRepository

    @Before
    override fun setUp() {
        endpoint = mockk(relaxed = true)
        repository = UserRepository(endpoint)
    }

    @Test
    fun `WHEN raise exception with status code 400 of POST, SHOULD convert to response`() =
        runTest {
            val message = "HTTP 400 Bad Request"
            setupExceptionMockPost()

            val result = repository.postUser(USER)
            val error = (result as ResultWrapper.Error).error as ClientException

            coVerify { endpoint.postUser(any()) }
            assertEquals(error.code, 400)
            assertEquals(error.message, message)
        }

    @Test
    fun `WHEN raise exception with status code 400, SHOULD convert to response`() =
        runTest {
            val message = "HTTP 400 Bad Request"
            setupExceptionMockGet(statusCode = 400, messageError = message)

            val result = repository.getUser()
            val error = (result as ResultWrapper.Error).error as ClientException

            coVerify { endpoint.getUser() }
            assertEquals(error.code, 400)
            assertEquals(error.message, message)
        }

    @Test
    fun `WHEN raise exception with status code 401, SHOULD convert to response`() =
        runTest {
            val message = "HTTP 401 Unauthorized"
            setupExceptionMockGet(statusCode = 401, messageError = message)

            val result = repository.getUser()
            val error = (result as ResultWrapper.Error).error as ClientException

            coVerify { endpoint.getUser() }
            assertEquals(error.code, 401)
            assertEquals(error.message, message)
        }

    @Test
    fun `WHEN raise exception with status code 403, SHOULD convert to response`() =
        runTest {
            val message = "HTTP 403 Forbidden"
            setupExceptionMockGet(statusCode = 403, messageError = message)

            val result = repository.getUser()
            val error = (result as ResultWrapper.Error).error as ClientException

            coVerify { endpoint.getUser() }
            assertEquals(error.code, 403)
            assertEquals(error.message, message)
        }

    @Test
    fun `WHEN raise exception with status code 404, SHOULD convert to response`() =
        runTest {
            val message = "HTTP 404 Not Found"
            setupExceptionMockGet(statusCode = 404, messageError = message)

            val result = repository.getUser()
            val error = (result as ResultWrapper.Error).error as ClientException

            coVerify { endpoint.getUser() }
            assertEquals(error.code, 404)
            assertEquals(error.message, message)
        }

    @Test
    fun `WHEN raise exception with status code 500, SHOULD convert to response`() =
        runTest {
            val message = "HTTP 500 Internal Server Error"
            setupExceptionMockGet(statusCode = 500, messageError = message)

            val result = repository.getUser()
            val error = (result as ResultWrapper.Error).error as ServerException

            coVerify { endpoint.getUser() }
            assertEquals(error.code, 500)
            assertEquals(error.message, message)
        }

    @Test
    fun `WHEN raise exception with status code 600, SHOULD convert to response`() =
        runTest {
            val message = "HTTP 600 Unknown Exception"
            setupExceptionMockGet(statusCode = 600, messageError = message)

            val result = repository.getUser()
            val error = (result as ResultWrapper.Error).error as UnknownResponseException

            coVerify { endpoint.getUser() }
            assertEquals(error.code, 600)
            assertEquals(error.message, message)
        }

    private fun setupExceptionMockGet(statusCode: Int, messageError: String) {
        val exception = mockk<HttpException> {
            every { code() } returns statusCode
            every { message } returns messageError
        }

        coEvery { endpoint.getUser() } throws exception
    }

    private fun setupExceptionMockPost() {
        val exception = mockk<HttpException> {
            every { code() } returns 400
            every { message } returns "HTTP 400 Bad Request"
        }

        coEvery { endpoint.postUser(any()) } throws exception
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