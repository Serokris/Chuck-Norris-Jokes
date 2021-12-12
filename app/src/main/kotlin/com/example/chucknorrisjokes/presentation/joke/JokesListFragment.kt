package com.example.chucknorrisjokes.presentation.joke

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentJokesListBinding
import com.example.chucknorrisjokes.presentation.MainActivity
import com.example.chucknorrisjokes.presentation.base.BaseBindingFragment
import com.example.chucknorrisjokes.utils.hide
import com.example.chucknorrisjokes.utils.show
import com.example.domain.models.Joke
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesListFragment :
    BaseBindingFragment<FragmentJokesListBinding>(FragmentJokesListBinding::inflate) {

    private val viewModel: JokeListViewModel by viewModels()
    private var bottomNavigationView: BottomNavigationView? = null
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        JokesListAdapter()
    }
    private var jokesList = mutableListOf<Joke>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeUi()
    }

    private fun initViews() {
        val activity = activity as MainActivity
        activity.supportActionBar?.show()

        bottomNavigationView = activity.findViewById(R.id.bottomNav)

        with(binding) {
            recyclerView.adapter = adapter
            reloadButton.setOnClickListener {
                val count = countEditText.text.toString().toIntOrNull()
                val isDigitsOnly = countEditText.text.toString().isDigitsOnly()

                if (countEditText.text!!.isNotBlank() && isDigitsOnly) {
                    jokesList = emptyList<Joke>().toMutableList()
                    for (i in 0 until count!!) {
                        viewModel.fetchRandomJoke()
                    }
                    adapter.submitList(jokesList)
                } else {
                    Toast.makeText(
                        requireContext(), R.string.input_error, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        setHasOptionsMenu(true)
    }

    private fun subscribeUi() {
        viewModel.dataLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingJokesProgressBar.isVisible = isLoading
        }

        viewModel.joke.observe(viewLifecycleOwner) { joke ->
            jokesList.add(joke)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionHideInterface -> hideInterface()
            R.id.actionShowInterface -> showInterface()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideInterface() {
        bottomNavigationView?.hide()
        binding.countEditText.hide()
        binding.reloadButton.hide()
    }

    private fun showInterface() {
        bottomNavigationView?.show()
        binding.countEditText.show()
        binding.reloadButton.show()
    }
}