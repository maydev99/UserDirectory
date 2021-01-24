package com.bombadu.userdirectory.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.userdirectory.data.UserData
import com.bombadu.userdirectory.repository.UserRepository
import com.bombadu.userdirectory.util.Constants
import com.bombadu.userdirectory.util.Event
import com.bombadu.userdirectory.util.Resource
import kotlinx.coroutines.launch

class UserViewModel @ViewModelInject constructor(
    private val repository: UserRepository

) : ViewModel(){

    val userDatas = repository.observeAllUserData()

    private val _insertUserDataStatus = MutableLiveData<Event<Resource<UserData>>>()
    val insertUserDataStatus: LiveData<Event<Resource<UserData>>> = _insertUserDataStatus

    private val _currentUserData = MutableLiveData<Event<Resource<UserData>>>()
    val currentUserData: LiveData<Event<Resource<UserData>>> = _currentUserData

    fun setCurrentUserData(user : Event<Resource<UserData>>) {
        _currentUserData.postValue(user)
    }

    fun deleteUserData(userData: UserData) = viewModelScope.launch {
        repository.deleteUserData(userData)
    }

    fun insertUserDataIntoDB(userData: UserData) = viewModelScope.launch {
        repository.insertUserData(userData)
    }

    fun insertUserData(firstName: String, lastName: String, location: String, age: String) {
        if (firstName.isEmpty() || lastName.isEmpty() || location.isEmpty() || age.isEmpty()) {
            _insertUserDataStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }

        if (location.length < Constants.MIN_LOCATION_LENGTH) {
            _insertUserDataStatus.postValue(Event(Resource.error("Location must be at least 3 characters long", null)))
            return
        }

        val userData = UserData(firstName, lastName, location, age)
        insertUserDataIntoDB(userData)
        _insertUserDataStatus.postValue(Event(Resource.success(userData)))


    }


}