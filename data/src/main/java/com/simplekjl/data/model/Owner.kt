package com.simplekjl.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val imageUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("url")
    val profileUrl: String

)
