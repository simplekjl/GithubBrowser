package com.simplekjl.githubbrowser.ui

import com.simplekjl.githubbrowser.ui.model.RepositoriesViewEntity

/**
 * Contract class to be bind inside the layout
 */
data class ScreenState(
    val isLoading: Boolean = true,
    val showMessage: Boolean = false,
    val errorMessage: String? = null,
    val showList: Boolean = false,
    var itemsOnList: RepositoriesViewEntity = RepositoriesViewEntity()
)
