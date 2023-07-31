package me.mafu.dogslist_simplesurance.data

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import me.mafu.dogslist_simplesurance.CoroutineTestRule
import me.mafu.dogslist_simplesurance.data.local_data.LocalDataSource
import me.mafu.dogslist_simplesurance.data.remote_data.RemoteDataSource
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.domain.models.BreedsImage
import me.mafu.dogslist_simplesurance.domain.repositories.BreedsRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedsRepositoryImplTest {

    private lateinit var repository: BreedsRepository
    private val remoteDataSource = mockk<RemoteDataSource>()
    private val localDataSource = mockk<LocalDataSource>()

    private lateinit var breedsImage: BreedsImage
    private lateinit var breeds: Breeds

    @get:Rule
    val rule = CoroutineTestRule()

    @Before
    fun setUp() {
        breedsImage = BreedsImage(breedName = "akita", imageUrls = listOf("urls1", "url2"))
        breeds = Breeds("akita", "sub breeds", true)
        repository = BreedsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `getBreedsList - return breeds list`() = runTest {
        coEvery { localDataSource.getBreeds() } returns flow {
            emit(listOf(breeds))
        }

        coEvery { remoteDataSource.getBreeds() } returns Resource.Success(listOf(breeds))
        assertEquals(listOf(breeds), repository.getBreedsList().toList()[0])
    }

    @Test
    fun `getSingleBreeds - return a single breed`() = runTest {
        coEvery { localDataSource.getSingleBreeds(breeds.name) } returns flow {
            emit(breeds)
        }
        assertEquals(breeds, repository.getSingleBreeds(breeds.name).toList()[0])
    }

    @Test
    fun `getBreedsImagesList - return breeds image list`() = runTest {
        coEvery { localDataSource.getBreedsImage(breeds.name) } returns flow {
            emit(breedsImage)
        }
        assertEquals(breedsImage, repository.getBreedsImagesList(breeds.name).toList()[0])
    }

    @Test
    fun `getFavoriteBreedsList - return all favorite breeds`() = runTest {
        coEvery { localDataSource.getFavouriteBreeds() } returns flow {
            emit(listOf(breeds))
        }
        assertEquals(listOf(breeds), repository.getFavoriteBreedsList().toList()[0])
    }
}