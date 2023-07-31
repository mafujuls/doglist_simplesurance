package me.mafu.dogslist_simplesurance.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage

interface BreedsRepository {
    suspend fun getBreedsList() : Flow<List<Breeds>>
    suspend fun getSingleBreeds(name: String) : Flow<Breeds>
    suspend fun getBreedsImagesList(breedsName: String) : Flow<BreedsImage>
    suspend fun getFavoriteBreedsList() : Flow<List<Breeds>>
    suspend fun updateFavoriteBreedsList(breedsName: String, isFavourite: Boolean)
}