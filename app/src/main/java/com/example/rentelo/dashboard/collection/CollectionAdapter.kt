package com.example.rentelo.dashboard.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentelo.databinding.LayoutCollectionRentListBinding

class CollectionAdapter :
    ListAdapter<CollectionRent, CollectionAdapter.CollectionViewHolder>(ComparatorDiffUtils()) {

    class CollectionViewHolder(private val binding: LayoutCollectionRentListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(collectionRent: CollectionRent) {
            binding.collectionRentTitle.text = collectionRent.title
            Glide.with(itemView).load(collectionRent.image).into(binding.collectionRentImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = LayoutCollectionRentListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ComparatorDiffUtils : DiffUtil.ItemCallback<CollectionRent>() {
        override fun areItemsTheSame(oldItem: CollectionRent, newItem: CollectionRent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CollectionRent, newItem: CollectionRent): Boolean {
            return oldItem == newItem
        }
    }
}