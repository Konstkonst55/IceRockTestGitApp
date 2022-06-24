package com.example.icerocktestgitapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.icerocktestgitapp.R
import com.example.icerocktestgitapp.databinding.FragmentAuthBinding
import com.example.icerocktestgitapp.presentation.core.MainActivity
import com.example.icerocktestgitapp.utils.bindTextTwoWay
import com.example.icerocktestgitapp.utils.navigateToRepoList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar?.hide()

        with(binding) {
            viewModel.state.observe(viewLifecycleOwner){
                pbAuth.visibility = if (it is AuthViewModel.State.Loading) View.VISIBLE else View.GONE
                bSignIn.isEnabled = it !is AuthViewModel.State.Loading
                bSignIn.text = if(it is AuthViewModel.State.Loading) "" else getText(R.string.b_sign_in)
                tilUserToken.error = if (it is AuthViewModel.State.InvalidInput) it.reason else ""
            }

            bSignIn.setOnClickListener {
                viewModel.onSignButtonPressed()
            }

            tilUserToken.editText?.bindTextTwoWay(
                liveData = viewModel.token,
                lifecycleOwner = viewLifecycleOwner
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe(){
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.actions.collect {
                    when (it){
                        is AuthViewModel.Action.ShowError -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        is AuthViewModel.Action.RouteToMain -> navigateToRepoList()
                    }
                }
            }
        }
    }
}
