package com.example.rentelo.dashboard.featured

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentelo.databinding.LayoutFeaturedRentListBinding


class FeaturedRentAdapter :
    ListAdapter<FeaturedRent, FeaturedRentAdapter.FeaturedRentViewHolder>(ComparatorDiffUtils()) {

    class FeaturedRentViewHolder(private val binding: LayoutFeaturedRentListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(featuredRent: FeaturedRent) {
            binding.featuredRentLocation.text = featuredRent.location
            binding.featuredRentPrice.text = "Rs " + featuredRent.price + " /Month"
            Glide.with(itemView).load(featuredRent.image).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedRentViewHolder {
        val binding = LayoutFeaturedRentListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FeaturedRentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturedRentViewHolder, position: Int) {
        val featuredRent = getItem(position)
        holder.bind(featuredRent)
    }

    class ComparatorDiffUtils : DiffUtil.ItemCallback<FeaturedRent>() {
        override fun areItemsTheSame(oldItem: FeaturedRent, newItem: FeaturedRent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FeaturedRent, newItem: FeaturedRent): Boolean {
            return oldItem == newItem
        }
    }
}