package com.simplekjl.data.repository

import com.simplekjl.data.RepositoryContract
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
        return if (call.isSuccessful) {
            Result.Success(call.body()!!)
        } else {
            Result.Error(Exception(call.message()))
        }
    }

    override suspend fun getPersistedRepositories(): List<Repository> {
        localSource.getDbInstance()
        return emptyList()
    }
}
