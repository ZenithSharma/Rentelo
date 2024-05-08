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
import com.example.rentelo.dashboard.collection.CollectionAdapter
import com.example.rentelo.dashboard.collection.CollectionRentViewModel
import com.example.rentelo.dashboard.collection.CollectionRentViewModelFactory
import com.example.rentelo.dashboard.featured.FeaturedRentAdapter
import com.example.rentelo.dashboard.featured.FeaturedRentListViewModel
import com.example.rentelo.dashboard.featured.FeaturedRentListViewModelFactory
import com.example.rentelo.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FeaturedRentAdapter
    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var featuredRentListViewModel: FeaturedRentListViewModel
    private lateinit var collectionRentViewModel: CollectionRentViewModel

    @Inject
    lateinit var collectionRentViewModelFactory: CollectionRentViewModelFactory

    @Inject
    lateinit var featuredViewModelFactory: FeaturedRentListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        setUpFeaturedRv()
        setUpCollectionRv()
        setUpViewModel()
        observeLoader()
        observeFeatureRentList()
        observeCollectionRent()
        return binding.root
    }

    private fun observeCollectionRent() {
        collectionRentViewModel.collectionRentList.observe(this as LifecycleOwner){ collectionRentList ->

        }
    }

    private fun setUpCollectionRv() {
        collectionAdapter = CollectionAdapter()
        binding.collectionRentList.adapter = collectionAdapter
        binding.collectionRentList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
            if (featuredRentList.getOrNull() != null) {
                adapter.submitList(featuredRentList.getOrNull())
            } else {
                //TODO
            }
        }
    }

    private fun setUpViewModel() {
        featuredRentListViewModel =
            ViewModelProvider(this, featuredViewModelFactory)[FeaturedRentListViewModel::class.java]
        collectionRentViewModel = ViewModelProvider(
            this, collectionRentViewModelFactory
        )[CollectionRentViewModel::class.java]
    }

    private fun setUpFeaturedRv() {
        adapter = FeaturedRentAdapter()
        binding.featuredRentList.adapter = adapter
        binding.featuredRentList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}