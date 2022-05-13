package com.example.icerocktestgitapp.presentation.repoinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.icerocktestgitapp.databinding.FragmentRepositoriesBinding
import com.example.icerocktestgitapp.databinding.FragmentRepositoryViewBinding

class RepositoryInfoFragment : Fragment() {
    private lateinit var binding: FragmentRepositoryViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryViewBinding.inflate(inflater, container, false)

        return binding.root
    }
}