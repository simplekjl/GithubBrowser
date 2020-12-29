package com.simplekjl.data

import com.simplekjl.data.client.GithubService
import com.simplekjl.data.client.Result
import com.simplekjl.domain.model.RepositoriesPayload
import com.simplekjl.domain.model.Repository

class DataSourceRepository(
    private val network: NetworkSource,
    private val localSource: LocalSource
) : RepositoryContract {

    override suspend fun getMatchingRepositories(
        searchText: String
    ): Result<RepositoriesPayload> {
        val call = network.getGithubClient().searchRepositories(searchText)
        if (call.isSuccessful) {
            return Result.Success(call.body()!!)
        } else {
            return Result.Error(Exception(call.message()))
        }
    }

    override suspend fun getPersistedRepositories(): List<Repository> {
        localSource.getDbInstance()
        return emptyList()
    }
}

interface RepositoryContract {
    suspend fun getMatchingRepositories(searchText: String): Result<RepositoriesPayload>
    suspend fun getPersistedRepositories(): List<Repository>
}

interface NetworkSource {
    fun getGithubClient(): GithubService
}

interface LocalSource {
    fun getDbInstance()
}

interface FakeSource {
    fun getMockedContent()
}

interface BuiltType {
    fun getBuiltType(): String
}