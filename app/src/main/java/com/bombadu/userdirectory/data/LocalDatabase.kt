package com.bombadu.userdirectory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao

   /* private class LocalDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { _ ->
                scope.launch { }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "drink_database"
                ).addCallback(LocalDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

    }*/
}