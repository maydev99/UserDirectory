package com.bombadu.userdirectory.util

object DataUtil {

    /**
     * the input is not valid if..
     * ..the fields are not empty
     */

    fun validateAddFormInput(
        firstName: String,
        lastName: String,
        location: String,
        age: String
    ) : Boolean {

        if (firstName.isEmpty()) {
            return false
        }

        if (lastName.isEmpty()) {
            return false
        }

        if (location.isEmpty()) {
            return false
        }

        if (age.isEmpty()) {
            return false
        }

        if (location.length < 3) {
            return false
        }


        return true

    }

}