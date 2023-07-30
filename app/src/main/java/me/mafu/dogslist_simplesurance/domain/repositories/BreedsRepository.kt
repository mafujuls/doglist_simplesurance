package me.mafu.dogslist_simplesurance.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.domain.models.Breeds

interface BreedsRepository {
    suspend fun getBreedsList() : Flow<List<Breeds>>
    suspend fun getBreedsImagesList(breedsName: String) : Flow<List<String>>
    suspend fun getFavoriteBreedsList() : Flow<List<Breeds>>
    suspend fun updateFavoriteBreedsList(breedsName: String, isFavourite: Boolean)
}