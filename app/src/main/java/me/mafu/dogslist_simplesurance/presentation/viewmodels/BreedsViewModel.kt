package me.mafu.dogslist_simplesurance.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.usecases.GetBreedsUseCase
import me.mafu.dogslist_simplesurance.domain.usecases.UpdateBreedsToFavoriteUseCase
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val updateBreedsToFavoriteUseCase: UpdateBreedsToFavoriteUseCase
) : ViewModel() {

    suspend fun getBreeds() : Flow<List<Breeds>> {
        return getBreedsUseCase.execute()
    }

}