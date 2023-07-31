package me.mafu.dogslist_simplesurance.data.local_data

import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.data.local_data.db.BreedsDao
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: BreedsDao) : LocalDataSource {
    override suspend fun saveBreeds(breeds: List<Breeds>) {
        dao.saveAllBreeds(breeds)
    }

    override fun getBreeds(): Flow<List<Breeds>> {
        return dao.getAllBreeds()
    }

    override fun getSingleBreeds(name: String): Flow<Breeds> {
        return dao.getSingleBreeds(name)
    }

    override suspend fun getBreedsImage(breedsName: String): Flow<BreedsImage> {
        return dao.getBreedsImage(breedsName)
    }

    override suspend fun saveBreedsImage(breedsImage: BreedsImage) {
        dao.saveBreedsImages(breedsImage)
    }

    override suspend fun updateBreedsToFavorite(breedsName: String, isFavourite: Boolean) {
        dao.updateFavoriteBreeds(breedsName, isFavourite)
    }

    override fun getFavouriteBreeds(): Flow<List<Breeds>> {
        return dao.getFavouriteBreeds()
    }
}