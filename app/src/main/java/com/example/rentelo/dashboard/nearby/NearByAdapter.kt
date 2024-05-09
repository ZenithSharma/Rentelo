package com.example.rentelo.dashboard.nearby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentelo.databinding.LayoutNearByRentListBinding

class NearByAdapter : ListAdapter<NearBy, NearByAdapter.NearByViewHolder>(ComparatorDiffUtils()) {

    class NearByViewHolder(private val binding: LayoutNearByRentListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nearBy: NearBy) {
            binding.nearByRentLocation.text = nearBy.location
            binding.nearByRentPrice.text = "Rs " + nearBy.price + " /Month"
            Glide.with(itemView).load(nearBy.image).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearByViewHolder {
        val binding =
            LayoutNearByRentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NearByViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NearByViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ComparatorDiffUtils : DiffUtil.ItemCallback<NearBy>() {
        override fun areItemsTheSame(oldItem: NearBy, newItem: NearBy): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NearBy, newItem: NearBy): Boolean {
            return oldItem == newItem
        }
    }
}