package com.simplekjl.githubbrowser.framework

import com.simplekjl.data.repository.LocalSource
import com.simplekjl.domain.model.RepositoriesPayload
import com.simplekjl.githubbrowser.framework.database.AppDatabase
import com.simplekjl.githubbrowser.ui.mapper.RepositoriesPayloadMapper
import com.simplekjl.githubbrowser.ui.mapper.RepositoriesViewEntityMapper
import org.koin.java.KoinJavaComponent.inject

class InMemoryRepositories(
    private val mapper: RepositoriesViewEntityMapper,
    private val mapperToUi: RepositoriesPayloadMapper
) : LocalSource {
    private val database by inject(AppDatabase::class.java)

    override suspend fun getLocalRepositories(): RepositoriesPayload =
        mapper.mapUiToRaw(database.repositoriesDao().getAll())


    override suspend fun storeRepositories(repositoriesPayload: RepositoriesPayload) =
        database.repositoriesDao().insertPayload(mapperToUi.mapRawToUi(repositoriesPayload))
}
