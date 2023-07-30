package me.mafu.dogslist_simplesurance.data.remote_data

import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsDto
import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsImageDto
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor (private val apiService: BreedsApiService) : RemoteDataSource {
    override suspend fun getBreeds(): Resource<List<Breeds>> {
        try {
            val apiResponse = apiService.getDogBreeds()

            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let { body ->
                    if (body.status == BreedsDto.SUCCESS_STATUS) {
                        val breedsList = mutableListOf<Breeds>()
                        body.message.entries.forEach {
                            breedsList.add(
                                Breeds(
                                    it.key,
                                    it.value.joinToString(","),
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

    override suspend fun getBreedsImages(breedName: String): Resource<List<String>> {
        try {
            val apiResponse = apiService.getBreedsImages(breedName)
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let { body ->
                    if (body.status == BreedsImageDto.SUCCESS_STATUS) {
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