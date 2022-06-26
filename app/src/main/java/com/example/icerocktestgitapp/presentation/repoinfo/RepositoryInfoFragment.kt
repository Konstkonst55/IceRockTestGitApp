package com.example.icerocktestgitapp.presentation.repoinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.icerocktestgitapp.R
import com.example.icerocktestgitapp.databinding.FragmentRepositoryViewBinding
import com.example.icerocktestgitapp.presentation.core.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.noties.markwon.Markwon

@AndroidEntryPoint
class RepositoryInfoFragment : Fragment() {
    private lateinit var binding: FragmentRepositoryViewBinding
    private var markwon: Markwon? = null
    private val viewModel by viewModels<RepositoryInfoViewModel>()
    private val args: RepositoryInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryViewBinding.inflate(inflater, container, false)

        init()
        setToolbar()

        return binding.root
    }

    private fun setToolbar() {
        (requireActivity() as MainActivity).supportActionBar?.run{
            title = args.nameRepo
            setDisplayHomeAsUpEnabled(true)
            show()
        }
    }

    private fun init() {
        markwon = Markwon.create(requireContext())
        viewModel.loadRepoInfo(args.idRepo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        markwon = null
        super.onDestroyView()
    }

    private fun observe(){
        viewModel.state.observe(viewLifecycleOwner) { state ->
            with(binding) {
                pbReposView.visibility = if(state is RepositoryInfoViewModel.State.Loading) View.VISIBLE else View.GONE
                gInfo.visibility = if(state is RepositoryInfoViewModel.State.Loading) View.GONE else View.VISIBLE
                iRepoInfoConError.root.visibility = if(state is RepositoryInfoViewModel.State.ConnectionError) View.VISIBLE else View.GONE

                if(state is RepositoryInfoViewModel.State.Loaded){
                    val readmeState = state.readmeState
                    bLinkGit.text = state.githubRepo.link
                    tvLicenseName.text = state.githubRepo.license?.name ?: "-"
                    tvReposForks.text = Html.fromHtml(getString(R.string.repos_forks, state.githubRepo.forks))
                    tvReposStars.text = Html.fromHtml(getString(R.string.repos_stars, state.githubRepo.stars))
                    tvReposWatchers.text = Html.fromHtml(getString(R.string.repos_watchers, state.githubRepo.watchers))
                    bLinkGit.setOnClickListener { openLink(state.githubRepo.link) }

                    pbReadme.visibility = if(readmeState is RepositoryInfoViewModel.ReadmeState.Loading) View.VISIBLE else View.GONE
                    tvReadme.text = if(readmeState is RepositoryInfoViewModel.ReadmeState.Empty) getString(R.string.no_readme) else if(readmeState is RepositoryInfoViewModel.ReadmeState.Error) readmeState.error else ""
                    if(readmeState is RepositoryInfoViewModel.ReadmeState.Loaded) markwon?.setMarkdown(tvReadme, readmeState.markdown)
                    iReadmeConError.root.visibility = if(readmeState is RepositoryInfoViewModel.ReadmeState.ConnectionError) View.VISIBLE else View.GONE
                    iReadmeConError.bUpdateListConnectionError.setOnClickListener { viewModel.loadRepoInfo(args.idRepo) }
                }
            }
        }
    }

    private fun openLink(url: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }catch (ex: Exception){
            Snackbar.make(requireView(), R.string.open_error, Snackbar.LENGTH_LONG).show()
        }
    }
}