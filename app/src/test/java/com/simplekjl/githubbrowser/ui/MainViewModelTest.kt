package com.simplekjl.githubbrowser.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.data.client.GithubService
import com.simplekjl.data.client.Result
import com.simplekjl.data.repository.DataSourceRepository
import com.simplekjl.data.repository.NetworkSource
import com.simplekjl.domain.model.RepositoriesPayload
import com.simplekjl.githubbrowser.framework.StringProvider
import com.simplekjl.githubbrowser.ui.mapper.RepositoriesPayloadMapper
import com.simplekjl.githubbrowser.ui.mapper.RepositoriesViewEntityMapper
import com.simplekjl.usecases.GetRepositoriesByKeyWord
import com.simplekjl.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class MainViewModelTest {

    @Rule
    @JvmField
    val ruleForLiveData = InstantTaskExecutorRule()

    @Mock
    lateinit var mockLiveDataObserver: Observer<ScreenState>

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val networkSourceMock: NetworkSource = mock()
    private var mockRepository: DataSourceRepository = mock()
    private val mapperUi: RepositoriesViewEntityMapper = mock()
    private val mapperRaw: RepositoriesPayloadMapper = mock()
    private val usecase: GetRepositoriesByKeyWord = mock()
    private val clientMock: GithubService = mock()

    private val stringProvider: StringProvider = mock()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(usecase, mapperRaw, stringProvider)
        viewModel.state.observeForever(mockLiveDataObserver)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun searchRepositories() {
        runBlockingTest {
            whenever(networkSourceMock.getGithubClient()).thenReturn(clientMock)
            whenever(mockRepository.getMatchingRepositories("word")).thenReturn(
                Result.Success(
                    RepositoriesPayload()
                )
            )

        }
        viewModel.searchRepositories("word")
        assert(
            viewModel.state.getOrAwaitValue().isLoading == ScreenState(true).isLoading
        )
        verify(mockLiveDataObserver).onChanged(ScreenState())

    }

}
