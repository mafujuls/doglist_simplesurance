package me.mafu.dogslist_simplesurance.data.remote_data

import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsDto
import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsApiService {
    @GET("breeds/list/all")
    suspend fun getDogBreeds(): Response<BreedsDto>

    @GET("breed/{breed_name}/images/random/6")
    suspend fun getBreedsImages(@Path("breed_name") breedName: String): Response<BreedsImageDto>
}