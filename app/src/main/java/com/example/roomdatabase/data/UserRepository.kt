package com.example.roomdatabase.data

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor.
// Pass in the DAO instead of the whole database, because you only need access to the DAO
class UserRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<User>> = userDao.getAll()

    // Suspend tells the compiler this needs to be called from an async / suspending function.
    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }
}