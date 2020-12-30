package com.simplekjl.githubbrowser.ui.adapter

import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity

interface OnItemClick {
    fun onRepositoryClicked(repositoryViewEntity: RepositoryViewEntity)
}
