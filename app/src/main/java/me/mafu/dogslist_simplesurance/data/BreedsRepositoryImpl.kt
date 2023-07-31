package me.mafu.dogslist_simplesurance.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.mafu.dogslist_simplesurance.data.local_data.LocalDataSource
import me.mafu.dogslist_simplesurance.data.remote_data.RemoteDataSource
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage
import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository

class BreedsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : BreedsRepository {

    /**
     * This method will return all the breeds list. If the breeds list is not available in the
     * local database, this method will fetch the data from remote and will store in the local
     * database. And at the end, the breeds list will be returned.
     * @return a list of [Breeds] fetched from local db or from remote server
     */
    override suspend fun getBreedsList(): Flow<List<Breeds>> {
        return localDataSource.getBreeds().map {
            if (it.isEmpty()) {
                // if we don't have have data in the local db, we will fetch these
                // data from remote and store in the local db
                remoteDataSource.getBreeds().data?.let { breeds ->
                    localDataSource.saveBreeds(breeds)
                }
            }
            it
        }
    }

    /**
     * This method will return a single breeds list.
     * @param name -> the name of breeds which will breed will be returned.
     * @return a [Breeds] fetched local data source
     */
    override suspend fun getSingleBreeds(name: String): Flow<Breeds> {
        return localDataSource.getSingleBreeds(name)
    }


    /**
     * This method will return a list of one breed's images urls. If the list of urls is not
     * available in the local database, this method will fetch the urls from remote and will
     * store in the local database with the name. And at the end, the url list will be returned.
     * @return a list of [BreedsImage] fetched from local db or from remote server
     */
    override suspend fun getBreedsImagesList(breedsName: String): Flow<BreedsImage> {
        return localDataSource.getBreedsImage(breedsName).map {breedImage ->
            if (breedImage == null) {
                // if we don't have have image info in the local db, we will fetch these
                // image info from remote and store in the local db
                remoteDataSource.getBreedsImages(breedsName).data?.let { breedsImage ->
                    localDataSource.saveBreedsImage(BreedsImage(breedsImage, breedsName))
                }
            }
            breedImage
        }
    }

    override suspend fun getFavoriteBreedsList(): Flow<List<Breeds>> {
        return localDataSource.getFavouriteBreeds()
    }

    override suspend fun updateFavoriteBreedsList(breedsName: String, isFavourite: Boolean) {
        localDataSource.updateBreedsToFavorite(breedsName, isFavourite)
    }
}