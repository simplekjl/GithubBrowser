package com.simplekjl.githubbrowser.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simplekjl.githubbrowser.ui.model.RepositoriesViewEntity

@Dao
abstract class RepositoriesDao {
    @Query("SELECT * from $REPOSITORIES_TABLE")
    abstract suspend fun getAll(): RepositoriesViewEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPayload(order: RepositoriesViewEntity)

    @Query("DELETE FROM $REPOSITORIES_TABLE")
    abstract suspend fun delete()
}

