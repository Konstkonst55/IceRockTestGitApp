package com.example.icerocktestgitapp.data.models

import com.example.icerocktestgitapp.data.resources.Resource

data class RepoDetails(
    val repo: Repo,
    val readme: Resource<String>
)