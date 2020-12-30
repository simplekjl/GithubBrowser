package com.simplekjl.githubbrowser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.githubbrowser.databinding.RepositoryItemLayoutBinding
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity

class RepositoryAdapter(private val listener: OnItemClick) :
    ListAdapter<RepositoryViewEntity, RepositoryAdapter.RepositoryViewHolder>(RepositoryDiffUtil()) {

    private lateinit var repositoryItemLayoutBinding: RepositoryItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        repositoryItemLayoutBinding = RepositoryItemLayoutBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(repositoryItemLayoutBinding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RepositoryViewHolder(private val binding: RepositoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoryViewEntity) {
            binding.repository = item
            binding.cardRepositoryContainer.setOnClickListener { listener.onRepositoryClicked(item) }
            binding.executePendingBindings()
        }
    }
}
