package com.example.yieldmobile.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yieldmobile.data.AuthRepository
import com.example.yieldmobile.data.dto.LoginForm
import com.example.yieldmobile.data.dto.LoginValidationErrors
import com.example.yieldmobile.data.model.LoginScreenState
import com.example.yieldmobile.exceptions.retrofit2.ValidationException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,

) : ViewModel() {
    private val dispatcher = Dispatchers.IO

    private val _loginState: MutableLiveData<LoginScreenState> = MutableLiveData<LoginScreenState>(
        LoginScreenState()
    )

    val loginState: LiveData<LoginScreenState>
        get() = _loginState

    suspend fun attemptLogin(
        email: String,
        password: String,
        deviceName: String
    ): Boolean {
        var success = false
        try {
            clearError()
            setLoadingState(true)
            withContext(dispatcher) {
                val token = authRepository.login(LoginForm(email, password, deviceName))
                authRepository.storeApiTokenLocally(token)
                success = true
            }
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
}