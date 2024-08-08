package com.capstone.explorin.presentation.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.capstone.explorin.MainActivity
import com.capstone.explorin.R
import com.capstone.explorin.databinding.FragmentRegisterThirdBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterThirdFragment : Fragment() {

    private var _binding: FragmentRegisterThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        buttonListener()
    }

    private fun buttonListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnRegister.setOnClickListener {
            validateRegister()
        }
    }

    private fun validateRegister() {

        val args = this.arguments
        val firstName = args?.getString("firstName")
        val lastName = args?.getString("lastName")
        val email = args?.getString("email")


        val password = binding.edtSandiInput.text.toString().trim()
        val confirmPassword = binding.edtSandiConfirmationInput.text.toString().trim()

        if (email != null && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            goToHome(user)
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                requireContext(),
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            goToHome(null)
                        }
                    }
            } else {
                binding.edtSandiConfirmationInput.error = "Kata sandi tidak cocok"
            }
        } else {
            return
        }
    }

    private fun goToHome(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val TAG = "RegisterThirdFragment"

    }
}