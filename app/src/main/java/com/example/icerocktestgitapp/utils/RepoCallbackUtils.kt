package com.example.icerocktestgitapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.icerocktestgitapp.data.models.Repo

class RepoCallbackUtils: DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}