package com.example.icerocktestgitapp.utils

import com.example.icerocktestgitapp.presentation.auth.AuthFragment
import com.example.icerocktestgitapp.presentation.core.MainActivity
import com.example.icerocktestgitapp.presentation.repositories.RepositoriesListFragment

fun AuthFragment.navigateToRepoList(){
    (requireActivity() as MainActivity).navigateAuthToRepoList()
}

fun RepositoriesListFragment.navigateToDetailInfo(id: Int, name: String){
    (requireActivity() as MainActivity).navigateRepoListToDetail(id, name)
}