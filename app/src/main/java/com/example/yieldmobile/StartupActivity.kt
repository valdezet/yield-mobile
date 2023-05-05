package com.example.yieldmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.yieldmobile.application.RetrofitProvider
import com.example.yieldmobile.data.AuthRepository
import com.example.yieldmobile.data.AuthRetrofitApi
import com.example.yieldmobile.domain.GetPrefixedApiBearerTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartupActivity : AppCompatActivity() {
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
        val bearerToken = GetPrefixedApiBearerTokenUseCase()(applicationContext) ?: return false
        val repository = AuthRepository(RetrofitProvider.create().create(AuthRetrofitApi::class.java))
        return repository.checkToken(bearerToken)

    }
}