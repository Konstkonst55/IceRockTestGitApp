package com.example.icerocktestgitapp.data.repository

import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.data.models.RepoDetails
import com.example.icerocktestgitapp.data.models.UserInfo
import com.example.icerocktestgitapp.data.models.convertToUserInfo
import com.example.icerocktestgitapp.data.network.IGitApiService

class AppRepository constructor(
    private val gitApi: IGitApiService
){
//    suspend fun getRepositories(): List<Repo> {
//
//    }
//
//    suspend fun getRepository(repoId: String): RepoDetails {
//        // TODO:
//    }
//
//    suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): String {
//        // TODO:
//    }

    suspend fun signIn(token: String): UserInfo {
        return gitApi.signIn(token).convertToUserInfo()
    }

    // TODO:
}