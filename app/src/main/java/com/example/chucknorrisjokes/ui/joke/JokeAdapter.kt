package com.example.chucknorrisjokes.ui.joke

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.data.network.response.JokeResponse
import com.example.chucknorrisjokes.databinding.JokeItemBinding

class JokeAdapter : ListAdapter<JokeResponse, JokeAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: JokeResponse) {
            binding.jokeDescription.text = joke.description
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<JokeResponse>() {
        override fun areItemsTheSame(oldItem: JokeResponse, newItem: JokeResponse): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: JokeResponse, newItem: JokeResponse): Boolean = oldItem == newItem
    }
}