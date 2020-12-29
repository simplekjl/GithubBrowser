package com.simplekjl.data

import com.simplekjl.data.client.GithubService

class RepositoriesSource(
    private val network: NetworkSource
) {

    fun getMatchingRepositories(searchValue: String) {
        network.getGithubClient()
    }

}

interface NetworkSource {
    fun getGithubClient(): GithubService
}

interface LocalSource {
    fun getDbInstance()
}

interface FakeSource {
    fun getMockedContent()
}

interface BuiltType {
    fun getBuiltType(): String
}
