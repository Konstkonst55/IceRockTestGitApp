package com.example.icerocktestgitapp.presentation.core

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.icerocktestgitapp.R
import com.example.icerocktestgitapp.data.repository.IAuth
import com.example.icerocktestgitapp.data.resources.Resource
import com.example.icerocktestgitapp.databinding.ActivityMainBinding
import com.example.icerocktestgitapp.presentation.auth.AuthFragment
import com.example.icerocktestgitapp.presentation.auth.AuthFragmentDirections
import com.example.icerocktestgitapp.presentation.repositories.RepositoriesListFragmentDirections
import com.example.icerocktestgitapp.presentation.splash.splashFragmentDirections
import com.example.icerocktestgitapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @Inject lateinit var auth: IAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_IceRockTestGitApp)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.tbMain))

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val isEntered = savedInstanceState?.getBoolean(Constants.ENTERED_KEY)
        if (isEntered == null || isEntered == false) authorizationWithTokenFromStorage()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (navController.currentDestination != navController.findDestination(R.id.splashFragment)) {
            outState.putBoolean(Constants.ENTERED_KEY, true)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_sign_out ->{
                lifecycleScope.launch{
                    auth.signOut()
                }
                navController.popBackStack(R.id.repositoriesListFragment,true)
                navController.navigate(R.id.authFragment)
                hideProgressBar()
            }
            android.R.id.home ->{
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun navigateAuthToRepoList(){
        navController.navigate(
            AuthFragmentDirections.actionAuthFragmentToRepositoriesListFragment()
        )
    }

    private fun navigateSplashToRepoList(){
        navController.navigate(
            splashFragmentDirections.actionSplashFragmentToRepositoriesListFragment()
        )
    }

    fun navigateRepoListToDetail(id: Int, name: String){
        navController.navigate(
            RepositoriesListFragmentDirections.actionRepositoriesListFragmentToRepositoryInfoFragment(id, name)
        )
    }

    private fun navigateToAuth(){
        navController.navigate(
            splashFragmentDirections.actionSplashFragmentToAuthFragment()
        )
    }

    private fun hideProgressBar(){
        binding.pbMain.visibility = View.GONE
    }

    private fun authorizationWithTokenFromStorage(){
        lifecycleScope.launch{
            when(val res = auth.signIn()){
                is Resource.Success -> {
                    navigateSplashToRepoList()
                    hideProgressBar()
                }
                is Resource.Error -> if (res.code == Constants.UNAUTHORIZED_CODE){
                    navController.popBackStack(R.id.authFragment,true)
                    navigateToAuth()
                    hideProgressBar()
                }else{
                    hideProgressBar()
                    navigateSplashToRepoList()
                }
            }
        }
    }
}