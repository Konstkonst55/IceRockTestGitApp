package com.example.icerocktestgitapp.presentation.auth

import androidx.lifecycle.*
import com.example.icerocktestgitapp.data.resources.Resource
import com.example.icerocktestgitapp.data.repository.IAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: IAuth
) : ViewModel() {

    val token = MutableLiveData("")

    private val _state: MutableLiveData<State> = MutableLiveData(State.Idle)
    val state: LiveData<State>
        get() = _state

    private val _actions: Channel<Action> = Channel(Channel.BUFFERED)
    val actions: Flow<Action>
        get() = _actions.receiveAsFlow()

    init {
        viewModelScope.launch { validate() }
    }

    private fun validate(): ValidationState{
        val result = token.value!!.validateToken()
        when(result){
            ValidationState.VALID -> _state.value = State.Idle
            ValidationState.INVALID -> _state.value = State.InvalidInput(result.reason)
            ValidationState.EMPTY -> {}
        }
        return result
    }

    private fun String.validateToken() : ValidationState{
        val pattern = "^[a-zA-Z0-9_-]{0,45}\$"
        return if (this.isEmpty()) ValidationState.EMPTY
        else{
            val matcher = Pattern.compile(pattern).matcher(this)
            if(matcher.matches()) ValidationState.VALID
            else ValidationState.INVALID
        }
    }

    private fun signIn(){
        viewModelScope.launch {
            _state.value = State.Loading
            when (val res = auth.signIn(token.value)){
                is Resource.Success -> {
                    _actions.send(Action.RouteToMain)
                    _state.postValue(State.Idle)
                }
                is Resource.Error -> {
                    _actions.send(Action.ShowError(res.message!!))
                    _state.postValue(State.Idle)
                }
            }
        }
    }

    fun onSignButtonPressed() {
        when(val result = validate()){
            ValidationState.VALID -> signIn()
            ValidationState.EMPTY -> _state.value = State.InvalidInput(result.reason)
            ValidationState.INVALID -> {}
        }
    }

    sealed interface State {
        object Idle : State
        object Loading : State
        data class InvalidInput(val reason: String) : State
    }

    sealed interface Action {
        data class ShowError(val message: String) : Action
        object RouteToMain : Action
    }

    enum class ValidationState(val reason: String){
        EMPTY("Empty input"),
        INVALID("Invalid input"),
        VALID("Valid input")
    }
}