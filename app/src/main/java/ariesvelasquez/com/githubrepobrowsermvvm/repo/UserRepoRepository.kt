package ariesvelasquez.com.githubrepobrowsermvvm.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ariesvelasquez.com.githubrepobrowsermvvm.AppExecutors
import ariesvelasquez.com.githubrepobrowsermvvm.api.UserRepositoryService
import ariesvelasquez.com.githubrepobrowsermvvm.db.UserRepositoryDao
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
class UserRepoRepository(
    private val repoDao: UserRepositoryDao,
    private val repoSourceService: UserRepositoryService,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    fun getUserRepositories(user: String) : LiveData<Resource<List<UserRepository>?>> {
        return object : NetworkBoundResource<List<UserRepository>, List<UserRepositorySource>> (appExecutors) {
            override fun saveCallResult(items: List<UserRepositorySource>) {

//                val mappedItems: List<UserRepository> = Transformations.switchMap(items, UserRepository(name = items.))
                val newList = mutableListOf<UserRepository>()
                items.forEachIndexed { index, userRepositorySource ->
                    newList.add(UserRepository(
                        name = userRepositorySource.name,
                        description = userRepositorySource.description))
                }

                repoDao.insertRepositores(newList)
            }

            override fun shouldFetch(data: List<UserRepository>?) = true

            override fun loadFromDb() = repoDao.getRepositories()

            override fun createCall() = repoSourceService.getUserRepos(user)

        }.asLiveData()
    }
}