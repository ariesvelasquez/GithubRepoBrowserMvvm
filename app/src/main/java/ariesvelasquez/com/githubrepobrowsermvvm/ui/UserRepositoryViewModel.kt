package ariesvelasquez.com.githubrepobrowsermvvm.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ariesvelasquez.com.githubrepobrowsermvvm.api.UserRepositoryService
import ariesvelasquez.com.githubrepobrowsermvvm.db.UserRepositoriesDatabase
import ariesvelasquez.com.githubrepobrowsermvvm.model.UserRepository
import ariesvelasquez.com.githubrepobrowsermvvm.model.network.Resource
import ariesvelasquez.com.githubrepobrowsermvvm.repo.UserRepoRepository

class UserRepositoryViewModel(application: Application): AndroidViewModel(application) {

    private var userRepoRepository: UserRepoRepository = UserRepoRepository(
        UserRepositoriesDatabase.getDatabase(application).userRepositoriesDao(),
        UserRepositoryService.getUserRepositoryService()
    )

    fun getUserRepos(user: String): LiveData<Resource<List<UserRepository>?>> =
        userRepoRepository.getUserRepositories(user)
}