package com.example.yieldmobile.ui.login

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.yieldmobile.R
import com.example.yieldmobile.data.model.LoginScreenState
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginError: TextView
    private lateinit var loginButton: Button

    private val vm: LoginViewModel by viewModels { LoginViewModel.Factory }

    private val loadingStateObserver: Observer<LoginScreenState>
        get() {
            return Observer<LoginScreenState> {
                loginButton.isEnabled = !it.isLoading
            }
        }

    private val loginErrorObserver: Observer<LoginScreenState>
        get() {
            return Observer<LoginScreenState> {
                if (it.errorMessage == null) {
                    loginError.visibility = View.INVISIBLE
                    loginError.text = ""
                } else {
                    loginError.visibility = View.VISIBLE
                    loginError.text = it.errorMessage
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        bindViews(view)
        bindViewListeners()
        vm.loginState.observe(viewLifecycleOwner, loadingStateObserver)
        vm.loginState.observe(viewLifecycleOwner, loginErrorObserver)
        return view
    }

    private fun bindViews(fragmentView: View) {
        emailInput = fragmentView.findViewById(R.id.login_edit_email)
        passwordInput = fragmentView.findViewById(R.id.login_edit_password)
        loginError = fragmentView.findViewById(R.id.login_text_error)
        loginButton = fragmentView.findViewById(R.id.login_btn_submit)
    }

    private fun bindViewListeners() {
        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            lifecycleScope.launch {
                val result = vm.attemptLogin(
                    email,
                    password,
                    getDeviceNameForLogin()
                )
                result.toString()
            }

        }
    }

    private fun getDeviceNameForLogin(): String {
        val contentResolver = requireActivity().contentResolver
        val userDefinedDeviceName =
            Settings.Global.getString(contentResolver, "device_name")
        return "${android.os.Build.BRAND} $userDefinedDeviceName"
    }

}