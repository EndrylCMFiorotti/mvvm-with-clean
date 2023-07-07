package com.example.mvvm_with_clean.presenter.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.example.mvvm_with_clean.databinding.FragmentUserListBinding
import com.example.mvvm_with_clean.domain.presentation.UserPresentation
import com.example.mvvm_with_clean.presenter.adapter.UserListAdapter
import com.example.mvvm_with_clean.presenter.viewModel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private val viewModel by viewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        setUpRequestApp()
        bottomSheetConfig()
    }

    private fun bottomSheetConfig() {
        binding.flBottomSheet.isVisible = false
        BottomSheetBehavior.from(binding.flBottomSheet).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun setUpRequestApp() {
        startLoading()
        viewModel.getUser()
        refreshApp()
    }

    private fun setUpObservers() {
        with(viewModel) {
            userList.observe(viewLifecycleOwner) { list ->
                recyclerViewConfig(list)
            }
            isEmpty.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "List is empty.", Toast.LENGTH_SHORT).show()
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "There was an error. ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun recyclerViewConfig(list: List<UserPresentation>) {
        binding.rvUsers.adapter = UserListAdapter(list) {
            bottomSheetLayout(it)
        }
        binding.swipeToRefresh.isRefreshing = false
        stopLoading()
    }

    private fun bottomSheetLayout(it: UserPresentation) {
        with(binding) {
            flBottomSheet.isVisible = true
            ivUserImage.load(it.image)
            tvUserName.text = it.name
            tvUserEmail.text = it.email
        }
    }

    private fun startLoading() {
        with(binding) {
            rvUsers.isVisible = false
            shimmerRv.apply {
                isVisible = true
                startShimmer()
            }
        }
    }

    private fun stopLoading() {
        with(binding) {
            rvUsers.isVisible = true
            shimmerRv.apply {
                isVisible = false
                stopShimmer()
            }
        }
    }

    private fun refreshApp() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getUser()
        }
    }
}