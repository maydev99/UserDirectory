package com.bombadu.userdirectory.util

/**
 * Purpose - So Live Data emit one time events
 */

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * returns the content , even if it's already been handled.
     */

    fun peekContent(): T = content

}