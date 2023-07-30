package me.mafu.dogslist_simplesurance.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.mafu.dogslist_simplesurance.data.local_data.LocalDataSource
import me.mafu.dogslist_simplesurance.data.remote_data.RemoteDataSource
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage
import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : BreedsRepository {
    override suspend fun getBreedsList(): Flow<List<Breeds>> {
        return localDataSource.getBreeds().map {
            if (it.isEmpty()) {
                remoteDataSource.getBreeds().data?.let { breeds ->
                    localDataSource.saveBreeds(breeds)
                }
            }
            it
        }
    }

    override suspend fun getBreedsImagesList(breedsName: String): Flow<List<String>> {
        return localDataSource.getBreedsImage(breedsName).map {
            if (it.isEmpty()) {
                remoteDataSource.getBreedsImages(breedsName).data?.let { breedsImage ->
                    localDataSource.saveBreedsImage(BreedsImage(breedsImage, breedsName))
                }
            }
            it
        }
    }

    override suspend fun getFavoriteBreedsList(): Flow<List<Breeds>> {
        return localDataSource.getFavouriteBreeds()
    }

    override suspend fun updateFavoriteBreedsList(breedsName: String, isFavourite: Boolean) {
        localDataSource.updateBreedsToFavorite(breedsName, isFavourite)
    }
}