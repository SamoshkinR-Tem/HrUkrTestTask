package com.hr.ukr_tz.ui.frgmnt_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hr.ukr_tz.afterTextChanged
import com.hr.ukr_tz.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etUsername.afterTextChanged {
            viewModel.loginDataChanged(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString(),
                false
            )
        }

        binding.etPassword.apply {

            afterTextChanged {
                viewModel.loginDataChanged(
                    binding.etUsername.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etPassword.text.toString().count() > 0
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.login(
                            binding.etUsername.text.toString(),
                            binding.etPassword.text.toString()
                        )
                }
                false
            }
        }

        viewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            if (loginState.usernameError != null) {
                binding.etUsername.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.etPassword.error = getString(loginState.passwordError)
            }
        })
    }

    private fun setListeners() {

    }
}