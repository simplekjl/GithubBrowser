package com.simplekjl.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.data.client.GithubService
import com.simplekjl.data.client.Result
import com.simplekjl.domain.model.RepositoriesPayload
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class DataSourceRepositoryTest {

    private val networkSourceMock: NetworkSource = mock()
    private val localSourceMock: LocalSource = mock()
    private val clientMock: GithubService = mock()
    private lateinit var repositoryTest: DataSourceRepository

    @Before
    fun setup() {
        repositoryTest = DataSourceRepository(networkSourceMock, localSourceMock)
    }

    @Test
    fun `getMatchingRepositories from server `() {
        runBlockingTest {
            val expected = Result.Success(RepositoriesPayload())
            whenever(networkSourceMock.getGithubClient()).thenReturn(clientMock)
            whenever(networkSourceMock.getGithubClient().searchRepositories("word")).thenReturn(
                Response.success(RepositoriesPayload())
            )
            val result = repositoryTest.getMatchingRepositories("word")
            assert(result.toString() == expected.toString())
        }
    }

    @Test
    fun `get repositories database`() {
        runBlockingTest {
            val expected = RepositoriesPayload()
            whenever(localSourceMock.getLocalRepositories()).thenReturn(RepositoriesPayload())

            val result = repositoryTest.getPersistedRepositories()
            assert(result.toString() == expected.toString())
        }
    }
}
