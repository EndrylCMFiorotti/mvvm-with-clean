package com.example.mvvm_with_clean.presenter.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.mvvm_with_clean.R
import com.example.mvvm_with_clean.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonClick()
    }

    private fun buttonClick() {
        with(binding) {
            ibtList.setOnClickListener {
                configButton(it, R.id.action_to_userList, ibtRegister)
            }
            ibtRegister.setOnClickListener {
                configButton(it, R.id.action_to_register, ibtList)
            }
        }
    }

    private fun configButton(it: View, action: Int, disable: ImageButton) {
        findNavController(binding.fragment.id).navigate(action)
        disable.setBackgroundColor(Color.TRANSPARENT)
        it.background = ContextCompat.getDrawable(baseContext, R.drawable.bg_button)
    }
}