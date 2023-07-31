package me.mafu.dogslist_simplesurance.data.remote_data

import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsDto
import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsPhotoDto
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.domain.models.Breeds

class RemoteDataSourceImpl(private val apiService: BreedsApiService) : RemoteDataSource {

    /**
     * This method will fetch all the breeds from the remote server.
     * And the BreedsDtos from remote will be converted into Breeds model.
     * @return a list of [Breeds] by wrapping inside [Resource]
     */
    override suspend fun getBreeds(): Resource<List<Breeds>> {
        try {
            val apiResponse = apiService.getBreeds()

            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let { body ->

                    // We have data
                    if (body.status == BreedsDto.SUCCESS_STATUS) {
                        val breedsList = mutableListOf<Breeds>()
                        // converting the BreedsDtos to Breeds models
                        body.message.entries.forEach {
                            breedsList.add(
                                Breeds(
                                    it.key,
                                    it.value.joinToString(","), // concatenating all sub-breed by comma
                                    false,
                                )
                            )
                        }
                        return Resource.Success(data = breedsList)
                    } else {
                        return Resource.Error(message = "Error on breeds API response body.")
                    }
                }
                    ?: return Resource.Error(message = "Error on response body.\nError code: ${apiResponse.code()}")
            } else {
                return Resource.Error("Breeds API response unsuccessful")
            }
        } catch (exception: Exception) {
            return Resource.Error(message = "Network error \nException: ${exception.message}")
        }
    }

    /**
     * This method will fetch the image urls of a specific breed from the remote server.
     * @param -> the name of a breed
     * @return a list of [String] (urls) by wrapping inside [Resource]
     */
    override suspend fun getBreedsImages(breedName: String): Resource<List<String>> {
        try {
            val apiResponse = apiService.getBreedsPhoto(breedName)
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let { body ->
                    if (body.status == BreedsPhotoDto.SUCCESS_STATUS) {
                        return Resource.Success(data = body.message)
                    } else {
                        return Resource.Error(message = "Error on breeds API response body.")
                    }
                }
                    ?: return Resource.Error(message = "Error on image API response body.\nError code: ${apiResponse.code()}")
            } else {
                return Resource.Error("Image API response unsuccessful")
            }
        } catch (exception: Exception) {
            return Resource.Error(message = "Network error \nException: ${exception.message}")
        }

    }
}