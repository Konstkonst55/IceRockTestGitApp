package com.example.icerocktestgitapp.presentation.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.data.repository.IReposDetails
import com.example.icerocktestgitapp.data.resources.Resource
import com.example.icerocktestgitapp.presentation.auth.AuthViewModel
import com.example.icerocktestgitapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val repo: IReposDetails
) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State>
        get() = _state

    init {
        getRepoList()
    }

    fun getRepoList() {
        viewModelScope.launch {
            _state.value = State.Loading
            when (val res = repo.getRepositories()){
                is Resource.Success -> {
                    if (res.data!!.isEmpty()) _state.value = State.Empty
                    else _state.value = State.Loaded(res.data)
                }
                is Resource.Error -> {
                    if(res.message == Constants.CONNECTION_ERROR) _state.value = State.ConnectionError
                    else _state.value = State.Error(res.message!!)
                }
            }
        }
    }

    sealed interface State {
        object Loading : State
        data class Loaded(val repos: List<Repo>) : State
        data class Error(val error: String) : State
        object Empty : State
        object ConnectionError: State
    }
}