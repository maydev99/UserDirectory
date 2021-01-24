package com.bombadu.userdirectory.repository


import androidx.lifecycle.LiveData
import com.bombadu.userdirectory.data.LocalDao
import com.bombadu.userdirectory.data.UserData
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val localDao: LocalDao

) : UserRepository {
    override suspend fun insertUserData(userData: UserData) {
        localDao.insertData(userData)
    }

    override suspend fun deleteUserData(userData: UserData) {
        localDao.deleteData(userData)

    }

    override fun deleteAllData() {
        localDao.deleteAllData()
    }

    override fun observeAllUserData(): LiveData<List<UserData>> {
        return localDao.getAllData()

    }

    //Example of network Query (Retrofit) - Not needed for this app
    /*override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }*/

}