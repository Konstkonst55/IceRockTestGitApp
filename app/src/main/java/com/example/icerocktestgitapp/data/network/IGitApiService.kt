package com.example.icerocktestgitapp.data.network

import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IGitApiService {
    @GET("/user/repos")
    suspend fun getRepos(
        @Query("sort") sort: String = "updated",
        @Query("per_page") amount: Int = Constants.REPOSITORIES_COUNT
    ): Response<List<Repo>>
}