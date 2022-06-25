package com.example.icerocktestgitapp.data.models

import com.example.icerocktestgitapp.utils.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Repo(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val license: License? = null,
    val owner: Owner? = null,
    @SerialName(Constants.REPO_SERIAL_LINK) val link: String? = null,
    @SerialName(Constants.REPO_SERIAL_FORKS) val forks: Int? = null,
    @SerialName(Constants.REPO_SERIAL_STARS) val stars: Int = 0,
    @SerialName(Constants.REPO_SERIAL_WATCHERS) val watchers: Int = 0,
    @SerialName(Constants.REPO_SERIAL_BRANCH) val branch: String? = null,
    @Transient var color: String? = null
){
    @Serializable data class License(val name: String? = null)
    @Serializable data class Owner(@SerialName(Constants.REPO_SERIAL_OWNER_NAME) val name: String? = null)
}