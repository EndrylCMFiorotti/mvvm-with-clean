package com.example.mvvm_with_clean.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_with_clean.data.network.wrapper.ResultWrapper
import com.example.mvvm_with_clean.data.repository.UserRepository
import com.example.mvvm_with_clean.domain.presentation.UserPresentation
import com.example.mvvm_with_clean.domain.user.UserDto
import kotlinx.coroutines.Dispatchers
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
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getUser()) {
                is ResultWrapper.Success -> {
                    response.content.toUserListPresentation().let { list ->
                        when {
                            list.isEmpty() -> _isEmpty.postValue(Unit)
                            else -> _userList.postValue(list)
                        }
                    }
                }

                is ResultWrapper.Error -> _error.postValue(response.error)
            }
        }
    }

    fun postUser(user: UserDto) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.postUser(user)) {
                is ResultWrapper.Success -> response.content.let {
                    _register.postValue(it)
                }

                is ResultWrapper.Error -> response.error.let {
                    _error.postValue(it)
                }
            }
        }
    }
}