package me.mafu.dogslist_simplesurance.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage
import me.mafu.dogslist_simplesurance.domain.usecases.GetBreedsImagesUseCase
import me.mafu.dogslist_simplesurance.domain.usecases.GetSingleBreedsUseCase
import javax.inject.Inject

@HiltViewModel
class BreedsPhotoViewModel @Inject constructor(
    private val getBreedsImagesUseCase: GetBreedsImagesUseCase,
    private val getSingleBreedsUseCase: GetSingleBreedsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val breedsName = savedStateHandle.get<String>("breeds_name")

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<Resource<BreedsImage>>(Resource.Loading(null))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<Resource<BreedsImage>> = _uiState

    init {
        viewModelScope.launch {
            breedsName?.let { it ->
                getBreedsImagesUseCase.execute(it).collect { urls ->
                    _uiState.value = Resource.Success(urls)
                }
            }
        }
    }

    suspend fun getSingleBreeds() : Flow<Breeds>{
        return getSingleBreedsUseCase.execute(breedsName!!) //ToDo: Error prone! need to improve
    }
}
