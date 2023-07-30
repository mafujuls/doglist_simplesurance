package me.mafu.dogslist_simplesurance.data.local_data

import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage

interface LocalDataSource {
    suspend fun saveBreeds(breeds: List<Breeds>)
    fun getBreeds(): Flow<List<Breeds>>
    suspend fun getBreedsImage(breedsName: String): Flow<List<String>>
    suspend fun saveBreedsImage(breedsImage: BreedsImage)
    suspend fun updateBreedsToFavorite(breedsName: String, isFavourite: Boolean)
    fun getFavouriteBreeds(): Flow<List<Breeds>>
}