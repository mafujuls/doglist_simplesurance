package me.mafu.dogslist_simplesurance.data.local_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.mafu.dogslist_simplesurance.data.utils.Converters
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage

@Database(
    entities = [Breeds::class, BreedsImage::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class BreedsDatabase : RoomDatabase() {
    abstract fun getBreedsDao() : BreedsDao
}