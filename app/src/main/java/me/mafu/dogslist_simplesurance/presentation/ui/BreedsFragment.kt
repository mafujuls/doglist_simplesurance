package me.mafu.dogslist_simplesurance.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.databinding.FragmentBreedsBinding
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.presentation.adapters.BreedsAdapter
import me.mafu.dogslist_simplesurance.presentation.viewmodels.BreedsViewModel

@AndroidEntryPoint
class BreedsFragment : BaseBreedFragment<FragmentBreedsBinding, BreedsViewModel>(),
    BreedsAdapter.BreedsItemClickListener {

    private val breedsAdapter = BreedsAdapter()

    override fun getViewModelClass(): Class<BreedsViewModel> {
        return BreedsViewModel::class.java
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBreedsBinding {
        return FragmentBreedsBinding.inflate(inflater, container, false)
    }

    override fun setUpViews() {
        //super.setUpViews()

        binding.fragmentBreedsRecyclerView.apply {
            adapter = breedsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        breedsAdapter.setOnClickListener(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is Resource.Loading -> binding.fragmentBreedsProgressBar.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            binding.fragmentBreedsProgressBar.visibility = View.GONE
                            breedsAdapter.differ.submitList(uiState.data)
                        }
                        is Resource.Error -> Log.d("ssdd_d", "Error...")
                    }
                }
            }
        }
    }

    override fun onFavoriteClickListener(breeds: Breeds) {
        lifecycleScope.launch {
            viewModel.updateFavoriteBreeds(breeds)
        }
    }

    override fun onBreedsClickListener(breeds: Breeds) {
        findNavController().navigate(
            BreedsFragmentDirections.actionBreedsFragmentToBreedPhotoFragment(
                breeds.name
            )
        )
    }
}