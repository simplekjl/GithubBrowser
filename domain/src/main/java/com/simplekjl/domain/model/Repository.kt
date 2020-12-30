package com.simplekjl.domain.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val repoName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("html_url")
    val repoUrl: String
)
