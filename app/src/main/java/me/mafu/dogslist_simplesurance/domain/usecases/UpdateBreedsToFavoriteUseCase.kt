package me.mafu.dogslist_simplesurance.domain.usecases

import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository
import javax.inject.Inject

class UpdateBreedsToFavoriteUseCase @Inject constructor(private val repository: BreedsRepository) {
    suspend fun execute(breeds: String, isFavourite: Boolean) {
        repository.updateFavoriteBreedsList(breeds, isFavourite)
    }
}