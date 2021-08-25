package com.example.chucknorrisjokes.ui.joke

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentJokesListBinding
import com.example.chucknorrisjokes.viewmodel.JokeViewModel
import kotlinx.coroutines.launch
import android.net.ConnectivityManager
import android.view.*
import androidx.fragment.app.viewModels
import com.example.chucknorrisjokes.data.network.response.JokeResponse
import com.example.chucknorrisjokes.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesListFragment : Fragment() {

    private val viewModel: JokeViewModel by viewModels()
    private lateinit var adapter: JokeAdapter
    private lateinit var binding: FragmentJokesListBinding

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        val activity = activity as MainActivity
        activity.supportActionBar?.show()

        bottomNavigation = activity.findViewById(R.id.bottomNav)!!

        binding = FragmentJokesListBinding.inflate(inflater)

        adapter = JokeAdapter()
        binding.recyclerView.adapter = adapter

        var jokeList = mutableListOf<JokeResponse>()

        binding.reloadButton.setOnClickListener {
            val count = binding.countEditText.text.toString().filter { it.isDigit() }
            val isDigitsOnly: Boolean = TextUtils.isDigitsOnly(binding.countEditText.text)

            viewModel.viewModelScope.launch {
                if (binding.countEditText.text!!.isNotEmpty() && isDigitsOnly && isInternetConnected()) {
                    for (i in 0 until count.toInt()) {
                        jokeList.add(viewModel.fetchRandomJoke())
                    }
                    adapter.submitList(jokeList)
                    jokeList = mutableListOf()
                } else {
                    Toast.makeText(requireContext(), R.string.input_error, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    private fun isInternetConnected() : Boolean {
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
        bottomNavigation.visibility = View.INVISIBLE
        binding.countEditText.visibility = View.INVISIBLE
        binding.reloadButton.visibility = View.INVISIBLE
    }

    private fun showInterface() {
        bottomNavigation.visibility = View.VISIBLE
        binding.countEditText.visibility = View.VISIBLE
        binding.reloadButton.visibility = View.VISIBLE
    }
}