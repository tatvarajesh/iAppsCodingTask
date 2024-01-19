package com.example.iappscodingtask.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.iappscodingtask.databinding.ItemPhotosBinding
import com.example.iappscodingtask.model.Items

class PhotoAdapter : ListAdapter<Items, PhotoAdapter.DataViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.published == newItem.published
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemPhotosBinding.inflate(LayoutInflater.from(parent.context))
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind()
    }

    inner class DataViewHolder(private val binding: ItemPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.items = getItem(adapterPosition)
            binding.root.setOnClickListener {
                try {
                    it.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(getItem(adapterPosition).link)
                        ), null
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}