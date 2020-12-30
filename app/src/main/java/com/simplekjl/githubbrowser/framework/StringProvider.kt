package com.simplekjl.githubbrowser.framework

import android.content.Context

class StringProvider(private val context: Context) {

    fun getString(resourceId: Int): String = context.getString(resourceId)
}
