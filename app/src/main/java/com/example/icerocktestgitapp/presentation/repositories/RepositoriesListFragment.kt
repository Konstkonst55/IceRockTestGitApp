package com.example.icerocktestgitapp.presentation.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.icerocktestgitapp.R
import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.databinding.FragmentAuthBinding
import com.example.icerocktestgitapp.databinding.FragmentRepositoriesBinding
import com.example.icerocktestgitapp.presentation.core.MainActivity
import com.example.icerocktestgitapp.presentation.repositories.adapter.RepoAdapter
import com.example.icerocktestgitapp.utils.navigateToDetailInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoriesListFragment : Fragment() {
    private lateinit var binding: FragmentRepositoriesBinding
    private val viewModel by viewModels<RepositoriesListViewModel>()
    private lateinit var adapter: RepoAdapter

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)

        binding.rvRepositories.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
                setDrawable(resources.getDrawable(R.drawable.item_divider, requireContext().theme))
            }
        )

        (requireActivity() as MainActivity).supportActionBar?.run{
            show()
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.repo_list_header)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        observe()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        binding.rvRepositories.adapter = null
        super.onDestroyView()
    }

    private fun setAdapter() {
        adapter = RepoAdapter{ id, name ->
            navigateToDetailInfo(id, name)
        }
        binding.rvRepositories.adapter = adapter
    }

    private fun observe(){
        viewModel.state.observe(viewLifecycleOwner) { state ->
            with(binding){
                if(state is RepositoriesListViewModel.State.Loaded) adapter.submitList(state.repos)

                if(state is RepositoriesListViewModel.State.Error) {
                    iSomethingError.root.visibility = View.VISIBLE
                    iSomethingError.tvSomethingErrorDescription.text = state.error
                } else { iSomethingError.root.visibility = View.GONE }

                pbRepoList.visibility = if(state is RepositoriesListViewModel.State.Loading) View.VISIBLE else View.GONE

                iEmptyError.root.visibility = if(state is RepositoriesListViewModel.State.Empty) View.VISIBLE else View.GONE

                iConnectionError.root.visibility = if(state is RepositoriesListViewModel.State.ConnectionError) View.VISIBLE else View.GONE

                iEmptyError.bUpdateListEmptyError.setOnClickListener { viewModel.getRepoList() }
                iSomethingError.bUpdateListSomethingError.setOnClickListener { viewModel.getRepoList() }
                iConnectionError.bUpdateListConnectionError.setOnClickListener { viewModel.getRepoList() }
            }
        }
    }
}