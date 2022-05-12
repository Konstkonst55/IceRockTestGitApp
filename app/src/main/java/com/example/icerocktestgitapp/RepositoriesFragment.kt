package com.example.icerocktestgitapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.icerocktestgitapp.databinding.FragmentAuthBinding
import com.example.icerocktestgitapp.databinding.FragmentRepositoriesBinding

class RepositoriesFragment : Fragment() {
    private lateinit var binding: FragmentRepositoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)

        return binding.root
    }
}