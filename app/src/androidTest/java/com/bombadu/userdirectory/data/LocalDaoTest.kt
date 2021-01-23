package com.bombadu.userdirectory.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.bombadu.userdirectory.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest //Unit Test
class LocalDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() //Solves 'job not complete yet error' live data

    private lateinit var database: LocalDatabase
    private lateinit var dao: LocalDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), //To get context
            LocalDatabase::class.java
        ).allowMainThreadQueries().build() //to run on single thread

        dao = database.localDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertUserData() = runBlockingTest {
        val myData = UserData("name1","name2","location","50", 1)
        dao.insertData(myData)

        val allLocalData = dao.getAllData().getOrAwaitValue() //use convenience class LiveDataUtilTest
        assertThat(allLocalData).contains(myData)

    }


    @Test
    fun deleteUserData() = runBlockingTest {
        val myData = UserData("name1","name2","location","50", 1)
        dao.insertData(myData)
        dao.deleteData(myData)

        val allLocalData = dao.getAllData().getOrAwaitValue() //use convenience class LiveDataUtilTest
        assertThat(allLocalData).doesNotContain(myData)

    }

    @Test
    fun deleteAllUserData() = runBlockingTest {
        val myData1 = UserData("name1","name2","location","50", 1)
        val myData2 = UserData("name1","name2","location","50", 2)
        val myData3 = UserData("name1","name2","location","50", 3)
        dao.insertData(myData1)
        dao.insertData(myData2)
        dao.insertData(myData3)

        dao.deleteAllData()

        val allLocalData = dao.getAllData().getOrAwaitValue() //use convenience class LiveDataUtilTest
        assertThat(allLocalData).doesNotContain(myData1)
        assertThat(allLocalData).doesNotContain(myData2)
        assertThat(allLocalData).doesNotContain(myData3)

    }


}