package com.simplekjl.data.repository

import com.simplekjl.data.client.Result
import com.simplekjl.domain.model.RepositoriesPayload

class DataSourceRepository(
    private val network: NetworkSource,
    private val localSource: LocalSource
) : RepositoryContract {

    override suspend fun getMatchingRepositories(
        searchText: String
    ): Result<RepositoriesPayload> {
        return try {
            val call = network.getGithubClient().searchRepositories(searchText)
            if (call.isSuccessful) {
                localSource.storeRepositories(call.body()!!)
                Result.Success(call.body()!!)
            } else {
                Result.Error(Exception(call.message()))
            }
        } catch (ex: Exception) {
            if (getPersistedRepositories().incompleteResults)
                Result.Error(Exception(ex.message))
            else
                Result.Success(getPersistedRepositories())
        }
    }

    override suspend fun getPersistedRepositories(): RepositoriesPayload {
        return localSource.getLocalRepositories()
    }
}

