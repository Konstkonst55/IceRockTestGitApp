package com.example.icerocktestgitapp.data.repository

import android.content.Context
import com.example.icerocktestgitapp.data.resources.Resource
import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.data.models.RepoDetails
import com.example.icerocktestgitapp.data.network.IGitApiService
import com.example.icerocktestgitapp.data.storage.KeyValueStorage
import com.example.icerocktestgitapp.utils.Constants
import com.example.icerocktestgitapp.utils.RetrofitRequestUtils
import com.example.icerocktestgitapp.utils.RetrofitRequestUtils.okHttpRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gitApi: IGitApiService,
    private val storage: KeyValueStorage,
    private val client: OkHttpClient,
) : IAuth, IReposDetails {

    private var repositoriesRequestResult: Resource<List<Repo>> = Resource.Error("")

    override suspend fun signIn(token: String?): Resource<Unit> = withContext(Dispatchers.IO){
        if (token != null){
            storage.authToken = token
        }
        val res = RetrofitRequestUtils.tryRequest{ gitApi.getRepos() }
        repositoriesRequestResult = if (res is Resource.Success){
            Resource.Success(selectLangColor(res.data.orEmpty()))
        }else{
            if(res.code == Constants.UNAUTHORIZED_CODE) storage.clearData()
            res
        }
        return@withContext res.toUnitResource()
    }

    private fun selectLangColor(repos: List<Repo>) : List<Repo>{
        val color: String = readAssets() ?: return repos
        val colors = try {
            JSONObject(color)
        }catch (er: JSONException){
            return repos
        }
        return repos.map{
            if (it.language != null){
                try {
                    it.color = colors.getString(it.language)
                }catch (er: JSONException) {}
            }
            it
        }
    }

    private fun readAssets(): String?{
        return try{
            context.assets.open(Constants.LANG_COLORS_FILE_DIR).bufferedReader().use { it.readText() }
        }catch (er: IOException){
            null
        }
    }

    override suspend fun signOut() {
        storage.clearData()
    }

    override suspend fun getRepositories(): Resource<List<Repo>> = withContext(Dispatchers.IO) {
        return@withContext repositoriesRequestResult
    }

    override suspend fun getRepository(repoId: Int): Resource<RepoDetails> = withContext(Dispatchers.IO) {
        val repo = repositoriesRequestResult.data!!.find { it.id == repoId }
        repo?.let {
            val readme = getReadme(
                repo.owner?.name ?: "",
                repo.name ?: "",
                repo.branch ?: ""
            )
            return@withContext Resource.Success(RepoDetails(repo, readme))
        }
        return@withContext Resource.Error("No repository with id")
    }

    private fun getReadme(ownerName: String, repositoryName: String, branchName: String) : Resource<String> {
        val url = Constants.BASE_README_URL + "$ownerName/$repositoryName/$branchName/${Constants.README_FILE_NAME}"
        return okHttpRequest(client, url)
    }
}