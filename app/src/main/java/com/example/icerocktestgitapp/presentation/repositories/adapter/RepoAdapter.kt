package com.example.icerocktestgitapp.presentation.repositories.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.icerocktestgitapp.data.models.Repo
import com.example.icerocktestgitapp.databinding.CardRepositoryBinding
import com.example.icerocktestgitapp.utils.RepoCallbackUtils

class RepoAdapter(
    private val onItemClick : (Int, String) -> Unit
) : ListAdapter<Repo, RepoAdapter.RepoViewHolder>(RepoCallbackUtils()) {

    class RepoViewHolder(
        private val binding: CardRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo){
            binding.tvRepositoryName.text = repo.name

            binding.tvLanguage.let{
                it.text = repo.language
                repo.color?.let { color ->
                    it.setTextColor(Color.parseColor(color))
                }
            }

            if(repo.description == null) binding.tvRepositoryDescription.visibility = View.GONE
            else binding.tvRepositoryDescription.text = repo.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            CardRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position).let{ repo ->
            holder.bind(repo)
            holder.itemView.setOnClickListener{ onItemClick(repo.id!!, repo.name!!) }
        }
    }
}