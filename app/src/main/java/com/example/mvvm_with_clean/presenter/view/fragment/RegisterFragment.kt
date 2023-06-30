package com.example.mvvm_with_clean.presenter.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvm_with_clean.R
import com.example.mvvm_with_clean.databinding.FragmentRegisterBinding
import com.example.mvvm_with_clean.domain.user.UserDto
import com.example.mvvm_with_clean.presenter.viewModel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
        clickButtonRegister()
    }

    private fun setUpObserver() {
        with(viewModel) {
            register.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Successful registration.", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_to_userList)
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Registration failed.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun clickButtonRegister() {
        with(binding) {
            btRegister.setOnClickListener {
                registerUser()
            }
        }
    }

    private fun FragmentRegisterBinding.registerUser() {
        viewModel.postUser(
            UserDto(
                name = etName.text.toString(),
                email = etEmail.text.toString(),
                password = etPassword.text.toString(),
                age = etAge.text.toString().toInt()
            )
        )
    }
}