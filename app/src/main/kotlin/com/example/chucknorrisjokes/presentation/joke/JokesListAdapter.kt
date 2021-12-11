package com.example.chucknorrisjokes.presentation.joke

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.databinding.JokeItemBinding
import com.example.domain.models.Joke

class JokesListAdapter : RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

    private val jokeList: MutableList<Joke> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            JokeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = jokeList[position]
        holder.bind(currentItem)
    }

    class ViewHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            binding.jokeDescription.text = joke.description
        }
    }

    override fun getItemCount(): Int = jokeList.size

    fun addJoke(joke: Joke) = jokeList.add(joke)

    fun clearJokeList() = jokeList.removeAll(jokeList)
}