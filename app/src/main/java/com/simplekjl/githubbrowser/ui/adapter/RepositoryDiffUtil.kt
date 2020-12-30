package com.simplekjl.githubbrowser.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity

class RepositoryDiffUtil : DiffUtil.ItemCallback<RepositoryViewEntity>() {
    override fun areItemsTheSame(
        oldItem: RepositoryViewEntity,
        newItem: RepositoryViewEntity
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: RepositoryViewEntity,
        newItem: RepositoryViewEntity
    ): Boolean = oldItem == newItem

}
