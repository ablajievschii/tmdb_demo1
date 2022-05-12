package com.example.android.tmdbdemo1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagingData.collectLatest { pagingData ->
                Timber.d("pagingData: $pagingData")
                (binding.items.adapter as ItemsAdapter).submitData(pagingData)
            }
        }

        binding.bGoToDetails.setOnClickListener {
//            val action = PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment()
//            findNavController().navigate(action)
            viewModel.accept(UiAction.LoadMore())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListAdapter() {
        binding.items.adapter = ItemsAdapter()
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.items.addItemDecoration(divider)
    }
}