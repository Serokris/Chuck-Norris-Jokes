package com.example.chucknorrisjokes.presentation.joke

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentJokesListBinding
import com.example.chucknorrisjokes.presentation.MainActivity
import com.example.chucknorrisjokes.presentation.base.BaseBindingFragment
import com.example.chucknorrisjokes.utils.hide
import com.example.chucknorrisjokes.utils.show
import com.example.domain.common.Result
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JokesListFragment :
    BaseBindingFragment<FragmentJokesListBinding>(FragmentJokesListBinding::inflate) {

    private val viewModel: JokeListViewModel by viewModels()
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val activity = activity as MainActivity
        activity.supportActionBar?.show()

        bottomNavigationView = activity.findViewById(R.id.bottomNav)

        val adapter = JokeListAdapter()
        binding.recyclerView.adapter = adapter

        binding.reloadButton.setOnClickListener {
            val count = binding.countEditText.text.toString().filter { it.isDigit() }
            val isDigitsOnly: Boolean = TextUtils.isDigitsOnly(binding.countEditText.text)

            viewModel.viewModelScope.launch {
                binding.apply {
                    if (countEditText.text!!.isNotEmpty() && isDigitsOnly && isInternetConnected()) {
                        adapter.clearJokeList()
                        for (i in 0 until count.toInt()) {
                            viewModel.fetchRandomJoke().onEach { result ->
                                when (result) {
                                    is Result.Success -> {
                                        loadingJokesProgressBar.hide()
                                        val joke = result.data!!
                                        adapter.addJoke(joke)
                                    }
                                    is Result.Loading -> {
                                        loadingJokesProgressBar.show()
                                    }
                                    is Result.Error -> {
                                        loadingJokesProgressBar.hide()
                                        Toast.makeText(
                                            requireContext(),
                                            result.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }.launchIn(viewModel.viewModelScope)
                        }
                    } else {
                        Toast.makeText(requireContext(), R.string.input_error, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
        setHasOptionsMenu(true)
    }

    private fun isInternetConnected(): Boolean {
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val netWorkInfo = connectivityManager.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.isConnected
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