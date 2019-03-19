package ariesvelasquez.com.githubrepobrowsermvvm.model

import com.google.gson.annotations.SerializedName

/**
 * Github Repository response describing its details
 * fetched from api source.
 *
 * @author Aries Velasquez
 * @since 3/20/2019.
 */
data class UserRepositorySource(
    @SerializedName("name") var name: String? = "",
    @SerializedName("items") var repositories: List<UserRepository> = emptyList()
)