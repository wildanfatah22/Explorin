package com.capstone.explorin.presentation.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.capstone.explorin.R
import com.capstone.explorin.databinding.FragmentRegisterSecondBinding

class RegisterSecondFragment : Fragment() {

    private var _binding: FragmentRegisterSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonListener()
    }

    private fun buttonListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnRegister.setOnClickListener {
            signInMethod()
        }
    }

    private fun signInMethod() {
        val args = this.arguments
        val firstName = args?.getString("firstName")
        val lastName = args?.getString("lastName")

        val email = binding.edtEmailInput.text.toString()

        val bundle = Bundle().apply {
            putString("firstName", firstName)
            putString("lastName", lastName)
            putString("email", email)
        }

        findNavController().navigate(R.id.action_registerSecondFragment_to_registerThirdFragment, bundle)
    }

}