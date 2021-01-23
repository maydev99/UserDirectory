package com.bombadu.userdirectory.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test


class DataUtilTest {

    @Test
    fun `empty first name returns false`() {
        val result = DataUtil.validateAddFormInput(
            "",
            "May",
            "Delray Beach",
            "50",
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `valid form returns true`() {
        val result = DataUtil.validateAddFormInput(
            "Michael",
            "May",
            "Delray Beach",
            "50",
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `empty last name returns false`() {
        val result = DataUtil.validateAddFormInput(
            "Michael",
            "",
            "Delray Beach",
            "50",
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `empty location returns false`() {
        val result = DataUtil.validateAddFormInput(
            "Michael",
            "May",
            "",
            "50",
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `empty age returns false`() {
        val result = DataUtil.validateAddFormInput(
            "Michael",
            "May",
            "Delray Beach",
            "",
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `location less than 3 chars returns false`() {
        val result = DataUtil.validateAddFormInput(
            "Michael",
            "May",
            "De",
            "",
        )

        assertThat(result).isFalse()
    }
}