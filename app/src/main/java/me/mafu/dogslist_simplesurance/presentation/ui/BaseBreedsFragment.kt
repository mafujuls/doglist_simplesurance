package me.mafu.dogslist_simplesurance.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import androidx.viewbinding.ViewBinding
import me.mafu.dogslist_simplesurance.R

abstract class BaseBreedFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    open var useSharedViewModel: Boolean = false

    private lateinit var _viewModel: VM
    protected val viewModel get() = _viewModel
    protected abstract fun getViewModelClass(): Class<VM>

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_anim)

        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    open fun setUpViews() {}

    private fun init() {
        _viewModel = if (useSharedViewModel) {
            ViewModelProvider(requireActivity()).get(
                getViewModelClass()
            )
        } else {
            ViewModelProvider(this).get(getViewModelClass())
        }
    }

    protected abstract fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}