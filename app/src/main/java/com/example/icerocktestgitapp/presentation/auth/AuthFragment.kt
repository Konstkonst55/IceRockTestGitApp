package com.example.icerocktestgitapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.icerocktestgitapp.databinding.FragmentAuthBinding
import com.example.icerocktestgitapp.presentation.core.MainActivity
import com.example.icerocktestgitapp.utils.navigateToRepoList

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        binding.bSignIn.setOnClickListener{ this.navigateToRepoList() }

        return binding.root
    }
}