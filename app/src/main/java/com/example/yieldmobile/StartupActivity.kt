package com.example.yieldmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.yieldmobile.data.AuthRepository
import com.example.yieldmobile.domain.GetPrefixedApiBearerTokenUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StartupActivity : AppCompatActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var getPrefixedApiBearerTokenUseCase : GetPrefixedApiBearerTokenUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToAppropriateActivity()
    }

    private fun navigateToAppropriateActivity() {
        lifecycleScope.launch(Dispatchers.IO) {
            if(checkForValidApiToken()) {
                startActivity(Intent(this@StartupActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@StartupActivity, AuthActivity::class.java))
                finish()
            }
        }

    }

    private suspend fun checkForValidApiToken(): Boolean {
        val bearerToken = getPrefixedApiBearerTokenUseCase() ?: return false
        return authRepository.checkToken(bearerToken)

    }
}