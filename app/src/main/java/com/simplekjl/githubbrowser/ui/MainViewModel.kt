package com.simplekjl.githubbrowser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplekjl.data.client.Result
import com.simplekjl.githubbrowser.ui.mapper.ResponseMapper
import com.simplekjl.githubbrowser.ui.model.RepositoriesViewEntity
import com.simplekjl.usecases.GetRepositoriesByKeyWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repositoriesUseCase: GetRepositoriesByKeyWord,
    private val responseMapper: ResponseMapper
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun searchRepositories(searchText: String) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val result = repositoriesUseCase.getRepositories(searchText)
                when (result) {
                    is Result.Success -> {
                        _state.postValue(
                            State.Data(
                                responseMapper.mapRawToUi(
                                    result.data
                                )
                            )
                        )
                    }
                    is Result.Error -> {
                        _state.postValue(State.Error(result.exception))
                    }
                }

            }
        }
    }

}

sealed class State {
    data class Data(val data: RepositoriesViewEntity) : State()
    data class Error(val ex: Exception) : State()
}
