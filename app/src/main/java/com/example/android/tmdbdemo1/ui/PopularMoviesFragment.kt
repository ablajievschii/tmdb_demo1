package com.example.android.tmdbdemo1.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.android.tmdbdemo1.R
import com.example.android.tmdbdemo1.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel by viewModels<PopularMoviesViewModel>()

    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListAdapter()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.accept(UiAction.LoadMore())
        }

        val pagingAdapter = (binding.items.adapter as ItemsAdapter)
        viewLifecycleOwner.lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { states ->
                binding.refreshLayout.isRefreshing = states.refresh is LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagingData.collectLatest { pagingData ->
                Timber.d("pagingData: $pagingData")
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListAdapter() {
        val adapter = ItemsAdapter()
        binding.items.adapter = adapter
        adapter.setOnClickListener() {uiModel, view ->
            (uiModel as? UiModel.DiscoverMovieItem)?.let {
                goToDetails(it)
            }
        }
        val space = resources.getDimensionPixelSize(R.dimen.list_item_space)
        binding.items.addItemDecoration(SpaceItemDecoration(
            Rect().apply {
                top = space
                bottom = space
                left = space
                right = space
            }
        ))
    }

    private fun goToDetails(uiModel: UiModel) {
        val action = PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment()
        findNavController().navigate(action)
    }
}