package com.simplekjl.usecases

import com.simplekjl.data.repository.DataSourceRepository
import com.simplekjl.data.client.Result
import com.simplekjl.domain.model.RepositoriesPayload

class GetRepositoriesByKeyWord(
    private val repository: DataSourceRepository
) {
    suspend fun getRepositories(searchText: String): Result<RepositoriesPayload> =
        repository.getMatchingRepositories(searchText)
}
