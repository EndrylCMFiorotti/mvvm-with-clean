package com.example.mvvm_with_clean.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_with_clean.R
import com.example.mvvm_with_clean.databinding.ItemListBinding
import com.example.mvvm_with_clean.domain.presentation.UserPresentation

class UserListAdapter(
    private val userList: List<UserPresentation>,
    private val onClick: (UserPresentation) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onClick(userData()) }
        }

        fun bind() {
            with(binding) {
                tvName.text = userData().name
                tvAge.text = tvAge.context.getString(R.string.presentation_age, userData().age)
                tvEmail.text = userData().email
            }
        }

        private fun userData() = userList[adapterPosition]
    }
}