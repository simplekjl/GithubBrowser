package com.simplekjl.githubbrowser.ui.mapper

import android.content.Context
import com.simplekjl.domain.model.Owner
import com.simplekjl.domain.model.RepositoriesPayload
import com.simplekjl.domain.model.Repository
import com.simplekjl.githubbrowser.R
import com.simplekjl.githubbrowser.ui.model.OwnerViewEntity
import com.simplekjl.githubbrowser.ui.model.RepositoriesViewEntity
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity

class RepositoriesPayloadMapper(private val applicationContext: Context) {

    fun mapRawToUi(raw: RepositoriesPayload): RepositoriesViewEntity {
        val repositoriesViewEntity = RepositoriesViewEntity()
        repositoriesViewEntity.incompleteResults = raw.incompleteResults
        repositoriesViewEntity.totalCount = raw.totalCount
        val newList = mutableListOf<RepositoryViewEntity>()
        raw.items.forEach {
            newList.add(mapRawRepositoryToUi(it))
        }
        repositoriesViewEntity.items = newList
        return repositoriesViewEntity
    }

    private fun mapRawOwnerToUi(raw: Owner): OwnerViewEntity {
        return OwnerViewEntity(
            uid = 0,
            username = raw.username,
            imageUrl = raw.imageUrl,
            reposUrl = raw.reposUrl,
            profileUrl = raw.profileUrl
        )
    }

    private fun mapRawRepositoryToUi(raw: Repository): RepositoryViewEntity {
        return RepositoryViewEntity(
            0, name = raw.name,
            repoName = raw.repoName,
            description = raw.description ?: applicationContext.getString(R.string.no_description),
            owner = mapRawOwnerToUi(raw.owner),
            repositoryUrl = raw.repoUrl
        )
    }
}
