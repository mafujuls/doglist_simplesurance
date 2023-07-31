package me.mafu.dogslist_simplesurance.presentation.viewmodels

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import me.mafu.dogslist_simplesurance.CoroutineTestRule
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.usecases.GetBreedsUseCase
import me.mafu.dogslist_simplesurance.domain.usecases.UpdateBreedsToFavoriteUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedsViewModelTest {

    private lateinit var viewModel: BreedsViewModel
    private val getBreedsUseCase = mockk<GetBreedsUseCase>()
    private val updateBreedsToFavoriteUseCase = mockk<UpdateBreedsToFavoriteUseCase>()

    private lateinit var breeds : Breeds

    @get:Rule
    val rule = CoroutineTestRule()

    @Before
    fun setUp() {
        breeds = Breeds("akita", "sub breeds", false)

        coEvery {
            getBreedsUseCase.execute()
        } returns  flow {
            emit(listOf(breeds))
        }

        viewModel = BreedsViewModel(getBreedsUseCase, updateBreedsToFavoriteUseCase)
    }

    @Test
    fun `getUiState - return the list of breeds`() = runTest {
        assertEquals(listOf(breeds), viewModel.uiState.value.data)
    }
}