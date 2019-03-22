package ariesvelasquez.com.githubrepobrowsermvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ariesvelasquez.com.githubrepobrowsermvvm.model.UserRepository

@Database(entities = [UserRepository::class], version = 1)
abstract class UserRepositoriesDatabase: RoomDatabase() {

    /**
     * Get User Repo Dao
     */
    abstract fun userRepositoriesDao(): UserRepositoryDao

    companion object {

        @Volatile
        private var INSTANCE: UserRepositoriesDatabase? = null

        fun getDatabase(context: Context) : UserRepositoriesDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, UserRepositoriesDatabase::class.java, "user-repo-db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}