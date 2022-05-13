package com.example.icerocktestgitapp.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.icerocktestgitapp.R
import com.example.icerocktestgitapp.databinding.ActivityMainBinding
import com.example.icerocktestgitapp.presentation.auth.AuthFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.clMain, AuthFragment())
            .addToBackStack(null)
            .commit()
    }
}