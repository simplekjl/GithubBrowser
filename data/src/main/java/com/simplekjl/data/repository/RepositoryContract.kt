package com.simplekjl.data.repository

import com.simplekjl.data.client.Result
import com.simplekjl.domain.model.RepositoriesPayload
import com.simplekjl.domain.model.Repository


interface RepositoryContract {
    suspend fun getMatchingRepositories(searchText: String): Result<RepositoriesPayload>
    suspend fun getPersistedRepositories(): List<Repository>
}
