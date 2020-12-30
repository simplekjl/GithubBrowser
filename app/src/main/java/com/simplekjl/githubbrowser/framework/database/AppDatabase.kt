package com.simplekjl.githubbrowser.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.simplekjl.githubbrowser.framework.database.converters.OwnerTypeConverter
import com.simplekjl.githubbrowser.framework.database.converters.RepositoryConverter
import com.simplekjl.githubbrowser.ui.model.OwnerViewEntity
import com.simplekjl.githubbrowser.ui.model.RepositoriesViewEntity
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity

@Database(
    entities = [RepositoriesViewEntity::class, OwnerViewEntity::class, RepositoryViewEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RepositoryConverter::class, OwnerTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoriesDao(): RepositoriesDao
}
