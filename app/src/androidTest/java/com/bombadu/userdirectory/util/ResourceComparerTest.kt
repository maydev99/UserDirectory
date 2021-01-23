package com.bombadu.userdirectory.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.bombadu.userdirectory.R
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    private lateinit var resourceComparer : ResourceComparer

    @Before
    fun setup() {
        resourceComparer = ResourceComparer()
    }


    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "UserDirectory")
        assertThat(result).isTrue()
    }


    @Test
    fun stringResourceDifferentAsGiven_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "")
        assertThat(result).isFalse()
    }
}