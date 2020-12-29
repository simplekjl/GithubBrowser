package com.simplekjl.githubbrowser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplekjl.githubbrowser.framework.RepositoriesSource
import com.simplekjl.githubbrowser.ui.mapper.ResponseMapper
import com.simplekjl.githubbrowser.ui.model.RepositoriesViewEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repositoriesSource: RepositoriesSource,
    private val responseMapper: ResponseMapper
) : ViewModel() {


    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun getRepositoriesTest() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                _state.postValue(
                    State.Data(
                        responseMapper.mapRawToUi(
                            repositoriesSource.getGithubClient().searchRepositories("GithubBrowser")
                                .body()!!
                        )
                    )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}

sealed class State {
    data class Data(val data: RepositoriesViewEntity) : State()
}
