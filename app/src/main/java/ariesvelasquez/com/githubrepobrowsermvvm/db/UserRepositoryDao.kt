package ariesvelasquez.com.githubrepobrowsermvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ariesvelasquez.com.githubrepobrowsermvvm.model.UserRepository


/**
 * Abstract access to the github repo database
 */
@Dao
interface UserRepositoryDao {

    @Insert
    fun insertRepositores(userRepositories: List<UserRepository>): List<Long>

    /**
     * Get all the repositories from database
     */
    @Query("SELECT * FROM user_repositories")
    fun getRepositories(): LiveData<List<UserRepository>>
}