package me.mafu.dogslist_simplesurance.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository
import javax.inject.Inject

class GetSingleBreedsUseCase @Inject constructor(private val repository: BreedsRepository) {
    suspend fun execute(name: String): Flow<Breeds> {
        return repository.getSingleBreedsList(name)
    }
}