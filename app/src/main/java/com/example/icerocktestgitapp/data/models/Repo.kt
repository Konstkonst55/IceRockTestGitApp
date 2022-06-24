package com.example.icerocktestgitapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Repo(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val license: String? = null,
    val owner: Owner? = null,
    @SerialName("html_url") val htmlUrl: String? = null,
    @SerialName("forks_count") val forks: Int? = null,
    @SerialName("stargazers_count") val stars: Int = 0,
    @SerialName("watchers_count") val watchers: Int = 0,
    @SerialName("default_branch") val branch: String? = null,
    @Transient var color: String? = null
){
    @Serializable data class License(val name: String? = null)
    @Serializable data class Owner(@SerialName("login") val name: String? = null)
}