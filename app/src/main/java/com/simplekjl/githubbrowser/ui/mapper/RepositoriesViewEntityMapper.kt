package com.simplekjl.githubbrowser.ui.mapper

import com.simplekjl.domain.model.Owner
import com.simplekjl.domain.model.RepositoriesPayload
import com.simplekjl.domain.model.Repository
import com.simplekjl.githubbrowser.ui.model.OwnerViewEntity
import com.simplekjl.githubbrowser.ui.model.RepositoriesViewEntity
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity

class RepositoriesViewEntityMapper {

    fun mapUiToRaw(itemUi: RepositoriesViewEntity): RepositoriesPayload {
        val payload = RepositoriesPayload()
        payload.incompleteResults = itemUi.incompleteResults
        payload.totalCount = itemUi.totalCount
        val newList = arrayListOf<Repository>()
        itemUi.items.forEach {
            newList.add(mapRawRepositoryToUi(it))
        }
        payload.items = newList
        return payload
    }

    private fun mapRawOwnerToUi(itemUi: OwnerViewEntity): Owner {
        return Owner(
            username = itemUi.username,
            imageUrl = itemUi.imageUrl,
            reposUrl = itemUi.reposUrl,
            profileUrl = itemUi.profileUrl
        )
    }

    private fun mapRawRepositoryToUi(itemUi: RepositoryViewEntity): Repository {
        return Repository(
            name = itemUi.name,
            repoName = itemUi.repoName,
            description = itemUi.description, owner = mapRawOwnerToUi(itemUi.owner),
            repoUrl = itemUi.repositoryUrl
        )
    }
}
