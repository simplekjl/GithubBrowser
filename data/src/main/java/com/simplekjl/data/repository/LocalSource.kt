package com.simplekjl.data.repository

import com.simplekjl.domain.model.RepositoriesPayload


interface LocalSource {
    suspend fun getLocalRepositories(): RepositoriesPayload
    suspend fun storeRepositories(repositoriesPayload: RepositoriesPayload)
}
