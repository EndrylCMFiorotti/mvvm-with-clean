package com.example.mvvm_with_clean.data.network

import com.example.mvvm_with_clean.data.network.interceptor.LoggingInterceptor
import com.example.mvvm_with_clean.util.CoroutineViewModelTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

@ExperimentalCoroutinesApi
class LoggingInterceptorTest : CoroutineViewModelTest() {
    @Test
    fun `WHEN BuildConfig DEBUG is true, SHOULD return Level BODY interceptor`() {
        val expectedInterceptor = HttpLoggingInterceptor()
        expectedInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val actualInterceptor = LoggerUtil.getInterceptor()

        assertEquals(expectedInterceptor.level, actualInterceptor.level)
    }

    @Test
    fun `WHEN BuildConfig DEBUG is false, SHOULD return Level NONE interceptor`() {
        val expectedInterceptor = HttpLoggingInterceptor()
        expectedInterceptor.level = HttpLoggingInterceptor.Level.NONE

        val actualInterceptor = LoggingInterceptor().getInterceptor()

        assertEquals(expectedInterceptor.level, actualInterceptor.level)
    }

    object LoggerUtil {
        fun getInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level =
                    if (DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    companion object {
        const val DEBUG = true
    }
}