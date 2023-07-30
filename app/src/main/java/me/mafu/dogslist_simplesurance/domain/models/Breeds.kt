package me.mafu.dogslist_simplesurance.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class Breeds(
    @PrimaryKey(autoGenerate = false)
    val name: String,

    @ColumnInfo(name = "sub_breeds")
    val subBreeds: String,

    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean
)
