package ariesvelasquez.com.githubrepobrowsermvvm.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ariesvelasquez.com.githubrepobrowsermvvm.AppExecutors
import ariesvelasquez.com.githubrepobrowsermvvm.api.GithubRepositoriesService
import ariesvelasquez.com.githubrepobrowsermvvm.model.UserRepository
import ariesvelasquez.com.githubrepobrowsermvvm.model.UserRepositorySource
import ariesvelasquez.com.githubrepobrowsermvvm.model.network.Resource

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 * @author Aries Jayson Velasquez
 * @since 3/20/2019.
 */
class GithubRepoRepository(
    private val repoSourceService: GithubRepositoriesService,
    private val appExecutors: AppExecutors
) {

    fun getUserRepositories(user: String) : LiveData<Resource<List<UserRepository>?>> {
        return object : NetworkBoundResource<List<UserRepository>, UserRepositorySource> (appExecutors) {
            override fun saveCallResult(item: UserRepositorySource) {

            }

            override fun shouldFetch(data: List<UserRepository>?) = true

            override fun loadFromDb() = MutableLiveData<List<UserRepository>>()

            override fun createCall() = repoSourceService.getUserRepos(user)

        }.asLiveData()
    }

}