package me.mafu.dogslist_simplesurance.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import me.mafu.dogslist_simplesurance.CoroutineTestRule
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage
import me.mafu.dogslist_simplesurance.domain.usecases.GetBreedsImagesUseCase
import me.mafu.dogslist_simplesurance.domain.usecases.GetSingleBreedsUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedsPhotoViewModelTest {
    private lateinit var viewModel: BreedsPhotoViewModel
    private val getBreedsImagesUseCase = mockk<GetBreedsImagesUseCase>()
    private val getSingleBreedsUseCase = mockk<GetSingleBreedsUseCase>()
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var breedsImage : BreedsImage
    private lateinit var breeds : Breeds

    @get:Rule
    val rule = CoroutineTestRule()

    @Before
    fun setUp() {
        breedsImage = BreedsImage(breedName = "akita", imageUrls = listOf("urls1", "url2"))
        breeds = Breeds("akita", "sub breeds", false)

        coEvery {
            getBreedsImagesUseCase.execute(breedsImage.breedName)
        } returns  flow {
            emit(breedsImage)
        }

        coEvery {
            getSingleBreedsUseCase.execute(breeds.name)
        } returns  flow {
            emit(breeds)
        }

        every {
            savedStateHandle.get<String>("breeds_name")
        } returns breedsImage.breedName

        viewModel = BreedsPhotoViewModel(getBreedsImagesUseCase, getSingleBreedsUseCase, savedStateHandle)
    }

    @Test
    fun `getUiState - return a breed's photo item`() = runTest{
        assertEquals(breedsImage, viewModel.uiState.value.data)
    }

    @Test
    fun `getSingleBreeds - return a single breed`() = runTest{
        assertEquals(breeds, viewModel.getSingleBreeds().toList()[0])
    }
}