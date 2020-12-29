package com.simplekjl.githubbrowser.framework

import com.simplekjl.data.NetworkSource
import com.simplekjl.data.client.GithubService
import org.koin.java.KoinJavaComponent.inject

class RepositoriesSource : NetworkSource {
    private val client by inject(GithubService::class.java)
    override fun getGithubClient(): GithubService = client
}
