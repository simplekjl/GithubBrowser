package com.simplekjl.domain.model

import com.google.gson.annotations.SerializedName

data class RepositoriesPayload(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: ArrayList<Repository>
)
