package me.mafu.dogslist_simplesurance.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.mafu.dogslist_simplesurance.R
import me.mafu.dogslist_simplesurance.databinding.FragmentBreedsBinding
import me.mafu.dogslist_simplesurance.presentation.viewmodels.BreedsViewModel

@AndroidEntryPoint
class BreedsFragment : BaseBreedFragment<FragmentBreedsBinding, BreedsViewModel>() {


    override fun setUpViews() {
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_BreedsFragment_to_FavoriteBreedsFragment)
        }
    }

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
}