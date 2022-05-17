package com.example.icerocktestgitapp.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.icerocktestgitapp.R
import com.example.icerocktestgitapp.databinding.ActivityMainBinding
import com.example.icerocktestgitapp.presentation.auth.AuthFragmentDirections
import com.example.icerocktestgitapp.presentation.repositories.RepositoriesListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_IceRockTestGitApp)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun navigateAuthToRepoList(){
        val action = AuthFragmentDirections.actionAuthFragmentToRepositoriesListFragment()
        navController.navigate(action)
    }

    fun navigateRepoListToRepoDetails(){
        val action =RepositoriesListFragmentDirections.actionRepositoriesListFragmentToRepositoryInfoFragment()
        navController.navigate(action)
    }
}