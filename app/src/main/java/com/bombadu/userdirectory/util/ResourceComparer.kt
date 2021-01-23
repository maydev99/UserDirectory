package com.bombadu.userdirectory.util


/**
 * For writing tests that require context in Android
 */

import android.content.Context

class ResourceComparer {

    fun isEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
    }
}