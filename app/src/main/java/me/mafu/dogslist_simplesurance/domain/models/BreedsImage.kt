package me.mafu.dogslist_simplesurance.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds_images")
class BreedsImage(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "image_urls")
    val imageUrls: List<String>,

    @ColumnInfo(name = "breeds_name")
    val breedName: String
)
