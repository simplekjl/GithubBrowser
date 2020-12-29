package com.simplekjl.data.client

import com.simplekjl.data.model.RepositoriesPayload
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubService {
    @Headers("Content-Type: application/json")
    @GET("repositories")
    suspend fun searchRepositories(@Query("q") searchValue: String): Response<RepositoriesPayload>

}
