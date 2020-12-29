package com.simplekjl.githubbrowser.ui.model

data class RepositoriesViewEntity(
    var totalCount: Int = 0,
    var incompleteResults: Boolean = true,
    var items: List<RepositoryViewEntity> = arrayListOf()
)

data class OwnerViewEntity(
    val username: String,
    val imageUrl: String,
    val reposUrl: String,
    val profileUrl: String
)

data class RepositoryViewEntity(
    val name: String,
    val repoName: String,
    val description: String,
    val owner: OwnerViewEntity
)
