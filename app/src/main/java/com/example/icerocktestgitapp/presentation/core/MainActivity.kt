package com.example.icerocktestgitapp.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.icerocktestgitapp.R
import com.example.icerocktestgitapp.databinding.ActivityMainBinding
import com.example.icerocktestgitapp.presentation.auth.AuthFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    private val navController = navHostFragment.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_IceRockTestGitApp)
        setContentView(binding.root)
    }
}