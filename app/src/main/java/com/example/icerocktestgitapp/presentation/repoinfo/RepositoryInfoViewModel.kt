package com.example.icerocktestgitapp.presentation.repoinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.data.repository.IReposDetails
import com.example.icerocktestgitapp.data.resources.Resource
import com.example.icerocktestgitapp.presentation.repositories.RepositoriesListViewModel
import com.example.icerocktestgitapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryInfoViewModel @Inject constructor(
    private val repo: IReposDetails
) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State>
        get() = _state

    fun loadRepoInfo(repoId: Int){
        viewModelScope.launch {
            when(val repoRes = repo.getRepository(repoId)){
                is Resource.Success -> {
                    ReadmeState.Loading
                    val readmeState = when(val readmeRes = repoRes.data!!.readme){
                        is Resource.Success -> {
                            if (readmeRes.data!!.isEmpty()) ReadmeState.Empty
                            else ReadmeState.Loaded(readmeRes.data)
                        }
                        is Resource.Error -> {
                            if(readmeRes.message == Constants.README_CONNECTION_ERROR) ReadmeState.ConnectionError
                            else ReadmeState.Error(readmeRes.message!!)
                        }
                    }
                    _state.value = State.Loaded(repoRes.data.repo, readmeState)
                }
                is Resource.Error -> {
                    if(repoRes.message == Constants.CONNECTION_ERROR) _state.value = State.ConnectionError
                    else _state.value = State.Error(repoRes.message!!)
                }
            }
        }
    }

    sealed interface State {
        object Loading : State
        object ConnectionError: State
        data class Error(val error: String) : State
        data class Loaded(val githubRepo: Repo, val readmeState: ReadmeState) : State
    }

    sealed interface ReadmeState {
        object Loading : ReadmeState
        object Empty : ReadmeState
        object ConnectionError: ReadmeState
        data class Error(val error: String) : ReadmeState
        data class Loaded(val markdown: String) : ReadmeState
    }
}