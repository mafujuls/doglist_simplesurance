package me.mafu.dogslist_simplesurance.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.databinding.FragmentBreedPhotoBinding
import me.mafu.dogslist_simplesurance.presentation.adapters.PhotoAdapter
import me.mafu.dogslist_simplesurance.presentation.viewmodels.BreedsPhotoViewModel
import java.util.*

@AndroidEntryPoint
class BreedPhotoFragment : BaseBreedFragment<FragmentBreedPhotoBinding, BreedsPhotoViewModel>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBreedPhotoBinding {
        return FragmentBreedPhotoBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass() = BreedsPhotoViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is Resource.Loading -> binding.fragmentBreedsPhotoProgressBar.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            binding.fragmentBreedsPhotoProgressBar.visibility =
                                View.GONE
                            binding.breedsPhotoGridView.adapter = uiState.data?.let {
                                PhotoAdapter(it.imageUrls)
                            }
                        }
                        is Resource.Error -> Log.d("ssdd_d", "Error...")
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getSingleBreeds().collect {
                    binding.breedsViewItemTitle.text = it.name
                    if (it.subBreeds.isEmpty()) {
                        binding.breedsViewItemSubtitle.visibility = View.GONE
                    } else {
                        binding.breedsViewItemSubtitle.text =
                            it.subBreeds.split(",").joinToString(", ") { singleName ->
                                singleName.replaceFirstChar { firstChar ->
                                    if (firstChar.isLowerCase()) firstChar.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }
                            }
                    }
                }
            }
        }
    }

}