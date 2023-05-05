package com.example.yieldmobile.ui.login

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.yieldmobile.application.RetrofitProvider
import com.example.yieldmobile.data.AuthRepository
import com.example.yieldmobile.data.AuthRetrofitApi
import com.example.yieldmobile.data.dto.LoginForm
import com.example.yieldmobile.data.dto.LoginValidationErrors
import com.example.yieldmobile.data.model.LoginScreenState
import com.example.yieldmobile.exceptions.retrofit2.ValidationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _loginState: MutableLiveData<LoginScreenState> = MutableLiveData<LoginScreenState>()
    val loginState: LiveData<LoginScreenState>
        get() = _loginState

    suspend fun attemptLogin(email: String, password: String, deviceName: String): Boolean {
        var success = false
        try {
            clearError()
            setLoadingState(true)
            withContext(dispatcher) {
                val token = authRepository.login(LoginForm(email, password, deviceName))
            }
            success = true
        } catch (ex: ValidationException) {
            val errors = ex.mapFieldErrors(LoginValidationErrors::class.java)
            val message = ex.errorData.message

            val loginError = errors.email?.get(0) ?: message
            _loginState.value = loginState.value?.apply {
                errorMessage = loginError
            }

        } finally {
            setLoadingState(false)
        }
        return success
    }

    private fun setLoadingState(newLoadState: Boolean) {
        _loginState.value = loginState.value?.apply { isLoading = newLoadState }
    }

    private fun clearError() {
        _loginState.value = loginState.value?.apply { errorMessage = null }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository =
                    AuthRepository(RetrofitProvider.create().create(AuthRetrofitApi::class.java))
                val instance = LoginViewModel(
                    repository
                )
                instance._loginState.value = LoginScreenState(null, false)
                return@initializer instance
            }
        }
    }
}