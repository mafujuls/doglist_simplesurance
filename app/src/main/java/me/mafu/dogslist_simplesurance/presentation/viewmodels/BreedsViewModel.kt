package me.mafu.dogslist_simplesurance.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.usecases.GetBreedsUseCase
import me.mafu.dogslist_simplesurance.domain.usecases.UpdateBreedsToFavoriteUseCase
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val updateBreedsToFavoriteUseCase: UpdateBreedsToFavoriteUseCase
) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<Resource<List<Breeds>>>(Resource.Loading(null))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<Resource<List<Breeds>>> = _uiState

    init {
        viewModelScope.launch {
            getBreedsUseCase.execute().collect {
                _uiState.value = Resource.Success(it.sortedBy { breed -> breed.name })
            }
        }
    }

    suspend fun updateFavoriteBreeds (breeds: Breeds) {
        updateBreedsToFavoriteUseCase.execute(breeds.name, !breeds.isFavourite)
    }
}
