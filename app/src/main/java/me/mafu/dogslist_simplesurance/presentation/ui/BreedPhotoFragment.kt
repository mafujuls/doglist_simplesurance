package me.mafu.dogslist_simplesurance.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.mafu.dogslist_simplesurance.databinding.FragmentBreedPhotoBinding
import me.mafu.dogslist_simplesurance.presentation.viewmodels.BreedsViewModel

@AndroidEntryPoint
class BreedPhotoFragment : BaseBreedFragment<FragmentBreedPhotoBinding, BreedsViewModel>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBreedPhotoBinding {
        return FragmentBreedPhotoBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass() = BreedsViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        //val vm = ViewModelProvider(this).get(BreedsViewModel::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getBreeds().collect{
                    it.forEach {
                        Log.d("ssdd_d", "breads name: ${it.name}")
                    }
                }
            }
        }
    }

}