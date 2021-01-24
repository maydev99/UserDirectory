package com.bombadu.userdirectory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bombadu.userdirectory.data.UserData

/**
 * Simulates Repository by using same interface
 */

class FakeUserRepository : UserRepository {

    private val userDatas = mutableListOf<UserData>()
    private val observableUserDatas = MutableLiveData<List<UserData>>(userDatas)
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableUserDatas.postValue(userDatas)

    }

    override suspend fun insertUserData(userData: UserData) {
        userDatas.add(userData)
        refreshLiveData()

    }

    override suspend fun deleteUserData(userData: UserData) {
        userDatas.remove(userData)
        refreshLiveData()

    }

    override fun deleteAllData() {
        userDatas.clear()
        refreshLiveData()

    }

    override fun observeAllUserData(): LiveData<List<UserData>> {
        return observableUserDatas

    }


}