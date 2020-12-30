package com.simplekjl.githubbrowser.framework.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.simplekjl.githubbrowser.ui.model.OwnerViewEntity

class OwnerTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToOwner(ownerRaw: String): OwnerViewEntity {
        return if (ownerRaw.isEmpty()) {
            OwnerViewEntity(0, "", "", "", "")
        } else {
            gson.fromJson(ownerRaw, OwnerViewEntity::class.java)
        }
    }

    @TypeConverter
    fun ownerToString(owner: OwnerViewEntity): String =
        gson.toJson(owner)
}
