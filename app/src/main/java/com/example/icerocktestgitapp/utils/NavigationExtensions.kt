package com.example.icerocktestgitapp.utils

import androidx.fragment.app.Fragment
import com.example.icerocktestgitapp.presentation.auth.AuthFragment
import com.example.icerocktestgitapp.presentation.core.MainActivity
import com.example.icerocktestgitapp.presentation.repositories.RepositoriesListFragment

fun AuthFragment.navigateToRepoList(){
    (requireActivity() as MainActivity).navigateAuthToRepoList()
}

fun RepositoriesListFragment.navigateToDetails(){
    (requireActivity() as MainActivity).navigateRepoListToRepoDetails()
}