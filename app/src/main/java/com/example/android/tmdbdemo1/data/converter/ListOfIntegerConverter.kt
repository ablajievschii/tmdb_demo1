package com.example.android.tmdbdemo1.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListOfIntegerConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromListOfStrings(value: List<Int>?): String? {
        val type = object : TypeToken<List<Int>>() {}.type
        return value?.let{ gson.toJson(it, type) }
    }

    @TypeConverter
    fun toListOfStrings(value: String?): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return value?.let { gson.fromJson(it, type) } ?: emptyList()
    }
}