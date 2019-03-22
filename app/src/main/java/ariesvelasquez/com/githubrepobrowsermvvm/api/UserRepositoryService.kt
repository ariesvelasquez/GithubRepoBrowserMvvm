package ariesvelasquez.com.githubrepobrowsermvvm.api

import androidx.lifecycle.LiveData
import ariesvelasquez.com.githubrepobrowsermvvm.model.UserRepository
import ariesvelasquez.com.githubrepobrowsermvvm.model.network.Resource
import ariesvelasquez.com.githubrepobrowsermvvm.model.UserRepositorySource
import ariesvelasquez.com.githubrepobrowsermvvm.utils.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api Methods to communicate with Github API
 *
 * @author Aries Jayson Velasquez
 * @since 3/20/2019.
 */
interface UserRepositoryService {

    /**
     * Get the list of github repository the [user] have from Github API.
     */
    @GET("/users/{user}/repos")
    fun getUserRepos(@Path("user") user: String) : LiveData<Resource<List<UserRepositorySource>>>

    /**
     * Instance Factory
     */
    companion object {
        private const val BASE_URL = "https://api.github.com/"

        // LoggingInterceptor
        private val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        fun getUserRepositoryService(): UserRepositoryService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(UserRepositoryService::class.java)
        }
    }
}