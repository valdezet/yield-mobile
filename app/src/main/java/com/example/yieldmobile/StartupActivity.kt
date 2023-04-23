package com.example.yieldmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToAppropriateActivity()
    }

    private fun navigateToAppropriateActivity() {
        /* TODO navigate to main activity when implemented and logged in */
        startActivity(Intent(this, AuthActivity::class.java))
        finish()

    }
}