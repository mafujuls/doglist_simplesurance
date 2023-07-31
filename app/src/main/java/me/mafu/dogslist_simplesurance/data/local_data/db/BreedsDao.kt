package me.mafu.dogslist_simplesurance.data.local_data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage

@Dao
interface BreedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllBreeds(breeds: List<Breeds>)

    @Query("SELECT * FROM breeds")
    fun getAllBreeds(): Flow<List<Breeds>>

    @Query("SELECT * FROM breeds WHERE name =:name")
    fun getSingleBreeds(name: String): Flow<Breeds>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBreedsImages(breedsImage: BreedsImage)

    @Query("SELECT * FROM breeds_images WHERE breeds_name =:breedsName")
    fun getBreedsImage(breedsName: String): Flow<BreedsImage>

    @Query("UPDATE breeds SET is_favourite = :isFavourite WHERE name =:name")
    suspend fun updateFavoriteBreeds(name: String, isFavourite: Boolean)

    @Query("SELECT * FROM breeds WHERE is_favourite = 1")
    fun getFavouriteBreeds(): Flow<List<Breeds>>

}