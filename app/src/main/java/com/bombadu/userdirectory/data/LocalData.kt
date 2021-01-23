package com.bombadu.userdirectory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
class LocalData(
    @ColumnInfo(name = "first_name") var first_name: String,
    @ColumnInfo(name = "last_name") var last_name: String,
    @ColumnInfo(name = "location") var location: String,
    @ColumnInfo(name = "age") var age: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}