package com.diegomedina.notesapp.view.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.diegomedina.notesapp.R
import com.diegomedina.notesapp.databinding.FragmentLoginBinding
import com.diegomedina.notesapp.helper.visibleIf
import com.diegomedina.notesapp.view.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer(this::loginSuccessChanged))
        viewModel.error.observe(viewLifecycleOwner, Observer(this::errorChanged))
        viewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStatusChanged))
        loginButton.setOnClickListener { viewModel.login() }
    }

    private fun loginSuccessChanged(success: Boolean) {
        if (success) {
            activity?.let {
                it.startActivity(Intent(it, HomeActivity::class.java))
                it.finish()
            }
        }
    }

    private fun errorChanged(exception: Throwable) {
        Toast
            .makeText(activity, getString(R.string.service_error), Toast.LENGTH_LONG)
            .show()
    }

    private fun loadingStatusChanged(isLoading: Boolean) {
        progressBar.visibleIf(isLoading)
        loginButton.visibleIf(!isLoading)
    }
}
