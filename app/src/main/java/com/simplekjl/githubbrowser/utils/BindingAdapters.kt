package com.simplekjl.githubbrowser.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.githubbrowser.R
import com.simplekjl.githubbrowser.ui.adapter.RepositoryAdapter
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity
import com.squareup.picasso.Picasso

@BindingAdapter("android:loadImage")
fun setImageViewResource(imageView: ImageView, resource: String) {
    Picasso.get()
        .load(resource)
        .placeholder(R.mipmap.ic_octo_cat)
        .resize(80, 80)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("android:loadRepositories", "android:repositoriesAdapter", requireAll = true)
fun setImageViewResource(
    recyclerView: RecyclerView,
    list: List<RepositoryViewEntity>,
    adapter: RepositoryAdapter
) {
    recyclerView.adapter = adapter
    adapter.submitList(list)
}
