package com.bombadu.userdirectory.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bombadu.userdirectory.MainCoroutineRule
import com.bombadu.userdirectory.repository.FakeUserRepository
import com.bombadu.userdirectory.util.Constants
import com.bombadu.userdirectory.util.Status
import com.bombadu.userdirectory.util.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        viewModel = UserViewModel(FakeUserRepository())
    }

    @Test
    fun `insert user item with empty field returns error` () {
        viewModel.insertUserData("name", "", "Here", "20")

        val value = viewModel.insertUserDataStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }


    @Test
    fun `insert valid user item returns true` () {
        viewModel.insertUserData("name", "last", "Here", "20")

        val value = viewModel.insertUserDataStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)

    }


    @Test
    fun `insert shopping item with too short location returns error` () {

        val string = buildString {
            for (i in 1 until Constants.MIN_LOCATION_LENGTH) {
                append(1)
            }
        }

        viewModel.insertUserData("name", "last", string, "20")

        val value = viewModel.insertUserDataStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }
}