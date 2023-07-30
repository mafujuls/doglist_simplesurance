package me.mafu.dogslist_simplesurance.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.mafu.dogslist_simplesurance.R
import me.mafu.dogslist_simplesurance.databinding.FragmentFavoriteBreedsBinding
import me.mafu.dogslist_simplesurance.presentation.viewmodels.BreedsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class FavoriteBreedsFragment : BaseBreedFragment<FragmentFavoriteBreedsBinding, BreedsViewModel>() {
    override fun setUpViews() {
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_FavoriteBreedsFragment_to_BreedPhotoFragment)
        }
    }

    override fun getViewModelClass(): Class<BreedsViewModel> {
        return BreedsViewModel::class.java
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoriteBreedsBinding {
        return FragmentFavoriteBreedsBinding.inflate(inflater, container, false)
    }
}