package com.daijun.sunflower.practice.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daijun.sunflower.practice.data.Plant
import com.daijun.sunflower.practice.databinding.ListItemPlantBinding
import com.daijun.sunflower.practice.fragments.HomeViewPagerFragmentDirections

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/13
 * @description
 */

class PlantAdapter : ListAdapter<Plant, PlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlantViewHolder(
            ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlantViewHolder(private val binding: ListItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.plant?.let { plant ->
                    navigateToPlant(plant, it)
                }
            }
        }

        fun bind(item: Plant) {
            binding.apply {
                plant = item
                executePendingBindings()
            }
        }

        private fun navigateToPlant(plant: Plant, view: View) {
            val direction = HomeViewPagerFragmentDirections
                .actionHomeViewPagerFragmentToPlantDetailFragment(plant.plantId)
            view.findNavController().navigate(direction)
        }
    }

}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant) =
        oldItem.plantId == newItem.plantId

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant) =
        oldItem == newItem
}