package com.simplekjl.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.data.client.Result
import com.simplekjl.data.repository.DataSourceRepository
import com.simplekjl.domain.model.RepositoriesPayload
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test


@ExperimentalCoroutinesApi
internal class GetRepositoriesByKeyWordTest {

    private val repository: DataSourceRepository = mock()

    @Test
    fun `get repositories using keyword `() {
        runBlockingTest {
            val expected = Result.Success(RepositoriesPayload())
            whenever(repository.getMatchingRepositories("github")).thenReturn(
                Result.Success(
                    RepositoriesPayload()
                )
            )
            val result: Result<RepositoriesPayload> = repository.getMatchingRepositories("github")
            assert(result.toString() == expected.toString())
        }
    }


}
