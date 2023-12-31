package me.mafu.dogslist_simplesurance.data.remote_data

import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsDto
import me.mafu.dogslist_simplesurance.data.remote_data.dtos.BreedsPhotoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsApiService {
    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<BreedsDto>

    @GET("breed/{breed_name}/images/random/6")
    suspend fun getBreedsPhoto(@Path("breed_name") breedName: String): Response<BreedsPhotoDto>
}