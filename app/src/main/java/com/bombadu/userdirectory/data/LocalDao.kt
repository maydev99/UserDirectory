package com.bombadu.userdirectory.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(userData: UserData)

    @Delete
    suspend fun deleteData(userData: UserData)

    @Query("DELETE FROM user_data_table")
    fun deleteAllData()

    @Query("SELECT * FROM user_data_table")
    fun getAllData(): LiveData<List<UserData>>

}