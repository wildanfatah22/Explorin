package com.capstone.explorin.presentation.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.capstone.explorin.R
import com.capstone.explorin.databinding.FragmentRegisterFirstBinding

class RegisterFirstFragment : Fragment() {
    private var _binding: FragmentRegisterFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        buttonListener()
    }

    private fun buttonListener() {
        binding.btnRegister.setOnClickListener {
            signInMethodFirst()
        }
    }

    private fun signInMethodFirst() {
        val firstName = binding.edtFirstNameInput.text.toString().trim()
        val lastName = binding.edtLastNameInput.text.toString().trim()

        val bundle = Bundle().apply {
            putString("firstName", firstName)
            putString("lastName", lastName)
        }

        findNavController().navigate(R.id.action_registerFirstFragment_to_registerSecondFragment, bundle)

    }
}