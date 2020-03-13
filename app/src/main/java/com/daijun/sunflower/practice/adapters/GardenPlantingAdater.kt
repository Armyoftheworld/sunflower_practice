package com.daijun.sunflower.practice.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daijun.sunflower.practice.data.PlantAndGardenPlantings
import com.daijun.sunflower.practice.databinding.ListItemGardenPlantingBinding
import com.daijun.sunflower.practice.fragments.HomeViewPagerFragmentDirections
import com.daijun.sunflower.practice.viewmodels.PlantAndGardenPlantingsViewModel

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/9
 * @description
 */
class GardenPlantingAdater :
    ListAdapter<PlantAndGardenPlantings, GardenPlantingAdater.ViewHolder>(GardenPlantDiffCallback()) {

    class ViewHolder(private val binding: ListItemGardenPlantingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.viewModel?.plantId?.let {
                    navigateToPlant(it, view)
                }
            }
        }

        private fun navigateToPlant(plantId: String, view: View) {
            val direction =
                HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToPlantDetailFragment(
                    plantId
                )
            view.findNavController().navigate(direction)
        }

        fun bind(plantAndGardenPlantings: PlantAndGardenPlantings) {
            binding.apply {
                viewModel = PlantAndGardenPlantingsViewModel(plantAndGardenPlantings)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemGardenPlantingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
    override fun areItemsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ) = oldItem.plant.plantId == newItem.plant.plantId

    override fun areContentsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ) = oldItem.plant == newItem.plant

}