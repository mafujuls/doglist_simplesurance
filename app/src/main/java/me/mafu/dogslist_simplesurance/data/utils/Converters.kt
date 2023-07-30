package me.mafu.dogslist_simplesurance.data.utils

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromStringToList(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toStringFromList(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}