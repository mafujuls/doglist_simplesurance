package me.mafu.dogslist_simplesurance.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage
import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository
import javax.inject.Inject

class GetBreedsImagesUseCase @Inject constructor(private val repository: BreedsRepository) {
    suspend fun execute(name: String): Flow<BreedsImage> {
        return repository.getBreedsImagesList(name)
    }
}