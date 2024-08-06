package com.capstone.explorin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.explorin.R
import com.capstone.explorin.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragmentManager()
    }

    private fun setFragmentManager() {
        val fragmentManager = supportFragmentManager
        val loginFragment = LoginFragment()
        val fragment = fragmentManager.findFragmentByTag(loginFragment::class.java.simpleName)

        if (fragment == null) {
            fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, loginFragment, LoginFragment::class.java.simpleName)
                .commit()
        }
    }
}