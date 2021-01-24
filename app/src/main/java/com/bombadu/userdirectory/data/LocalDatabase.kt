package com.bombadu.userdirectory.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao
}