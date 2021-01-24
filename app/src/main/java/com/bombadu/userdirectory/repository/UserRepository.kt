package com.bombadu.userdirectory.repository


import androidx.lifecycle.LiveData
import com.bombadu.userdirectory.data.UserData

//https://www.youtube.com/watch?v=JsktEQvoHEs

interface UserRepository {
    
    suspend fun insertUserData(userData: UserData)
    
    suspend fun deleteUserData(userData: UserData)

    fun deleteAllData()

    fun observeAllUserData(): LiveData<List<UserData>>


}