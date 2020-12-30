package com.simplekjl.githubbrowser.framework.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity

class RepositoryConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToRepositoryList(repositoriesListRaw: String): List<RepositoryViewEntity> {
        return if (repositoriesListRaw.isEmpty()) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<RepositoryViewEntity>>() {}.type
            gson.fromJson(repositoriesListRaw, listType)
        }
    }

    @TypeConverter
    fun repositoryListFromString(repositories: List<RepositoryViewEntity>): String =
        gson.toJson(repositories)
}



