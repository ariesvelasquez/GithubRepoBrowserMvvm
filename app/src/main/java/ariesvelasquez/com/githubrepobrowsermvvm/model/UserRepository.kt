package ariesvelasquez.com.githubrepobrowsermvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Github Repository response describing its details
 * fetched from api source.
 *
 * @author Aries Velasquez
 * @since 3/20/2019.
 */
@Entity(tableName = "user_repositories")
data class UserRepository(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null
)