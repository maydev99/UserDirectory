package com.bombadu.userdirectory.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_data_table")
data class UserData(
    var firstName: String,
    var lastName: String,
    var location: String,
    var age: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

)