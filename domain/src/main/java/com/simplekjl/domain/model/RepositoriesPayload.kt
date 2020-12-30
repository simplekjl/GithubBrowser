package com.simplekjl.domain.model

import com.google.gson.annotations.SerializedName

data class RepositoriesPayload(
    @SerializedName("total_count")
    var totalCount: Int = 0,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false,
    @SerializedName("items")
    var items: ArrayList<Repository> = arrayListOf()
)
