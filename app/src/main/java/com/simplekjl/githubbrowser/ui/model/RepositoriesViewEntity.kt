package com.simplekjl.githubbrowser.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.simplekjl.githubbrowser.framework.database.REPOSITORIES_TABLE
import com.simplekjl.githubbrowser.framework.database.converters.OwnerTypeConverter
import com.simplekjl.githubbrowser.framework.database.converters.RepositoryConverter

@Entity(tableName = REPOSITORIES_TABLE)
data class RepositoriesViewEntity(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    var totalCount: Int = 0,
    var incompleteResults: Boolean = true,
    @TypeConverters(RepositoryConverter::class)
    var items: List<RepositoryViewEntity> = arrayListOf()
)

@Entity
data class OwnerViewEntity(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    val username: String,
    val imageUrl: String,
    val reposUrl: String,
    var profileUrl: String
)

@Entity(tableName = "owner")
data class RepositoryViewEntity(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    var name: String,
    var repoName: String,
    var description: String,
    @TypeConverters(OwnerTypeConverter::class)
    var owner: OwnerViewEntity,
    var repositoryUrl: String
)
