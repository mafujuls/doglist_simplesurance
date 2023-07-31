package me.mafu.dogslist_simplesurance.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.mafu.dogslist_simplesurance.R
import me.mafu.dogslist_simplesurance.data.utils.Resource
import me.mafu.dogslist_simplesurance.databinding.FragmentFavoriteBreedsBinding
import me.mafu.dogslist_simplesurance.domain.models.Breeds
import me.mafu.dogslist_simplesurance.presentation.adapters.BreedsAdapter
import me.mafu.dogslist_simplesurance.presentation.viewmodels.BreedsFavoriteViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class FavoriteBreedsFragment :
    BaseBreedFragment<FragmentFavoriteBreedsBinding, BreedsFavoriteViewModel>(),
    BreedsAdapter.BreedsItemClickListener {
    private val breedsAdapter = BreedsAdapter()

    override fun getViewModelClass(): Class<BreedsFavoriteViewModel> {
        return BreedsFavoriteViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoriteBreedsBinding {
        return FragmentFavoriteBreedsBinding.inflate(inflater, container, false)
    }

    override fun setUpViews() {
        super.setUpViews()

        binding.fragmentBreedsFavoriteRecyclerView.apply {
            adapter = breedsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        breedsAdapter.setOnClickListener(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is Resource.Loading -> binding.fragmentBreedsFavoriteProgressBar.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            binding.fragmentBreedsFavoriteProgressBar.visibility = View.GONE
                            if (uiState.data.isNullOrEmpty()){
                                binding.fragmentBreedsFavoriteEmptyView.visibility = View.VISIBLE
                            } else {
                                binding.fragmentBreedsFavoriteEmptyView.visibility = View.GONE
                            }
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.updateFavoriteBreeds(breeds)
            }
        }
    }

    override fun onBreedsClickListener(breeds: Breeds) {
        findNavController().navigate(
            FavoriteBreedsFragmentDirections.actionFavoriteBreedsFragmentToBreedPhotoFragment(
                breeds.name
            )
        )
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item: MenuItem = menu.findItem(R.id.action_favorite)
        item.isVisible = false
    }
}