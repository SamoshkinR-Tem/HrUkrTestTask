package com.hr.ukr_tz.ui.frgmnt_main

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hr.ukr_tz.R

class MainViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    //----------------------------------------------------------------------------------------------------------------------
    fun loginDataChanged(username: String, password: String, checkPswrd: Boolean) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (checkPswrd && !isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else if(checkPswrd) {
            _loginForm.value = LoginFormState(isDataValid = true)
        } else {
            _loginForm.value = LoginFormState(isDataValid = false)
        }
    }

    // The username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // The password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    //----------------------------------------------------------------------------------------------------------------------
    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
//        val result = repository.login(username, password)
//
//        if (result is Result.Success) {
//            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
//        } else {
//            _loginResult.value = LoginResult(error = R.string.login_failed)
//        }
    }
}