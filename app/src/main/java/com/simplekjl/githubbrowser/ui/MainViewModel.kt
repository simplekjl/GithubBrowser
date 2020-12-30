package com.simplekjl.githubbrowser.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.simplekjl.data.client.Result
import com.simplekjl.githubbrowser.R
import com.simplekjl.githubbrowser.framework.StringProvider
import com.simplekjl.githubbrowser.ui.mapper.ResponseMapper
import com.simplekjl.usecases.GetRepositoriesByKeyWord
import kotlinx.coroutines.launch

class MainViewModel(
    private val repositoriesUseCase: GetRepositoriesByKeyWord,
    private val responseMapper: ResponseMapper,
    private val stringProvider: StringProvider
) : AndroidViewModel(Application()) {

    private val _state = MutableLiveData(
        ScreenState(
            isLoading = false, showMessage = true, stringProvider.getString(R.string.empty_message)
        )
    )
    val state: LiveData<ScreenState>
        get() = _state

    fun searchRepositories(searchText: String) {
        viewModelScope.launch {
            try {
                val result = repositoriesUseCase.getRepositories(searchText)
                when (result) {
                    is Result.Success -> {
                        _state.postValue(
                            ScreenState(
                                isLoading = false,
                                showMessage = false,
                                showList = true,
                                itemsOnList =
                                responseMapper.mapRawToUi(
                                    result.data
                                )
                            )
                        )
                    }
                    is Result.Error -> {
                        _state.postValue(
                            ScreenState(
                                isLoading = false,
                                showMessage = true,
                                errorMessage = stringProvider.getString(R.string.error_message)
                            )
                        )
                    }
                }
            } catch (ex: Exception) {
                Log.i(MainViewModel::class.java.name, ex.message.toString())
                _state.postValue(
                    ScreenState(
                        isLoading = false,
                        showMessage = true,
                        errorMessage = stringProvider.getString(R.string.network_error)
                    )
                )
            }
        }
    }

    fun loading() {
        _state.value = ScreenState(isLoading = true)
    }
}
