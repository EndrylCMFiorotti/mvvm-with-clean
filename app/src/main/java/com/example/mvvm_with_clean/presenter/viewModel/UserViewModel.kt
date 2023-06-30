package com.example.mvvm_with_clean.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_with_clean.data.network.wrapper.ResultWrapper
import com.example.mvvm_with_clean.data.repository.UserRepository
import com.example.mvvm_with_clean.data.response.UserResponse
import com.example.mvvm_with_clean.domain.presentation.UserPresentation
import com.example.mvvm_with_clean.domain.user.UserDto
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _userList = MutableLiveData<List<UserPresentation>>()
    val userList: LiveData<List<UserPresentation>> = _userList

    private val _register = MutableLiveData<Unit>()
    val register: LiveData<Unit> = _register

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> = _error

    private val _isEmpty = MutableLiveData<Unit>()
    val isEmpty: LiveData<Unit> = _isEmpty

    fun getUser() {
        viewModelScope.launch {
            when (val response = repository.getUser()) {
                is ResultWrapper.Success -> {
                    when {
                        response.content.isEmpty() -> _isEmpty.postValue(Unit)
                        else -> _userList.postValue(response.content.map { it.toUserPresentation() })
                    }
                }

                is ResultWrapper.Error -> _error.postValue(response.error)
            }
        }
    }

    fun postUser(user: UserDto) {
        viewModelScope.launch {
            when (val response = repository.postUser(user)) {
                is ResultWrapper.Success -> _register.postValue(Unit)

                is ResultWrapper.Error -> _error.postValue(response.error)
            }
        }
    }
}