package com.simplekjl.data.repository

import com.simplekjl.data.client.GithubService


interface NetworkSource {
    fun getGithubClient(): GithubService
}
