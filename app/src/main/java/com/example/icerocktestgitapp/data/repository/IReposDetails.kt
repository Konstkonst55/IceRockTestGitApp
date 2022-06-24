package com.example.icerocktestgitapp.data.repository

import com.example.icerocktestgitapp.data.resources.Resource
import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.data.models.RepoDetails

interface IReposDetails {
    suspend fun getRepositories(): Resource<List<Repo>>
    suspend fun getRepository(repoId: Int): Resource<RepoDetails>
    //suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): String
}