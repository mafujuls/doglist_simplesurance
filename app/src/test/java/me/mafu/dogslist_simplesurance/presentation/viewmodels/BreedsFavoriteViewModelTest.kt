package me.mafu.dogslist_simplesurance.presentation.viewmodels

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import me.mafu.dogslist_simplesurance.CoroutineTestRule
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.usecases.GetFavoriteBreedsUseCase
import me.mafu.dogslist_simplesurance.domain.usecases.UpdateBreedsToFavoriteUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedsFavoriteViewModelTest {

    private lateinit var viewModel: BreedsFavoriteViewModel
    private val getFavoriteBreedsUseCase = mockk<GetFavoriteBreedsUseCase>()
    private val updateBreedsToFavoriteUseCase = mockk<UpdateBreedsToFavoriteUseCase>()

    private lateinit var breeds : Breeds

    @get:Rule
    val rule = CoroutineTestRule()

    @Before
    fun setUp() {
        breeds = Breeds("akita", "sub breeds", true)

        coEvery {
            getFavoriteBreedsUseCase.execute()
        } returns  flow {
            emit(listOf(breeds))
        }

        viewModel = BreedsFavoriteViewModel(getFavoriteBreedsUseCase, updateBreedsToFavoriteUseCase)

    }

    @Test
    fun `getUiState - return list of breeds`() {
        assertEquals(viewModel.uiState.value.data, listOf(breeds))
    }
}