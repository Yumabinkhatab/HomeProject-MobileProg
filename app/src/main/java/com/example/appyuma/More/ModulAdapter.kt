package com.example.appyuma.More

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appyuma.databinding.ItemModulBinding

class ModulAdapter(
    private val modulList: List<ModulModel>,
    private val onItemClick: (ModulModel) -> Unit
) : RecyclerView.Adapter<ModulAdapter.ModulViewHolder>() {

    inner class ModulViewHolder(val binding: ItemModulBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModulViewHolder {
        val binding = ItemModulBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ModulViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModulViewHolder, position: Int) {
        val item = modulList[position]
        with(holder.binding) {
            tvModulName.text = item.name
            tvModulInfo.text = item.info
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgModul)
            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = modulList.size
}
