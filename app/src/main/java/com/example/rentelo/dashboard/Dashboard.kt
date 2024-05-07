package com.example.rentelo.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentelo.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Dashboard : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FeaturedRentAdapter
    private lateinit var featuredRentListViewModel: FeaturedRentListViewModel

    @Inject
    lateinit var viewModelFactory: FeaturedRentListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        setUpRv()
        setUpViewModel()
        observeLoader()
        observeFeatureRentList()
        return binding.root
    }

    private fun observeLoader() {
        featuredRentListViewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> binding.loader.visibility = View.VISIBLE
                false -> binding.loader.visibility = View.INVISIBLE
            }
        }
    }

    private fun observeFeatureRentList() {
        featuredRentListViewModel.featuredRentList.observe(this as LifecycleOwner) { featuredRentList ->
            Log.d("Featured Rent", featuredRentList.toString())

            if (featuredRentList.getOrNull() != null) {
                adapter.submitList(featuredRentList.getOrNull())
            } else {
                //TODO
            }

        }
    }

    private fun setUpViewModel() {
        featuredRentListViewModel =
            ViewModelProvider(this, viewModelFactory)[FeaturedRentListViewModel::class.java]
    }

    private fun setUpRv() {
        adapter = FeaturedRentAdapter()
        binding.featuredRentList.adapter = adapter
        binding.featuredRentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = Dashboard()
    }
}