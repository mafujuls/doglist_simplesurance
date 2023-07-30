package me.mafu.dogslist_simplesurance.data.remote_data

import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.domain.models.Breeds

interface RemoteDataSource {
    suspend fun getBreeds(): Resource<List<Breeds>>
    suspend fun getBreedsImages(breedName: String): Resource<List<String>>
}