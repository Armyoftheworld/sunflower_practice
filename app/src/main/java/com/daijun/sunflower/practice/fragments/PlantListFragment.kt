package com.daijun.sunflower.practice.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.daijun.sunflower.practice.R
import com.daijun.sunflower.practice.adapters.PlantAdapter
import com.daijun.sunflower.practice.databinding.FragmentPlantListBinding
import com.daijun.sunflower.practice.utilities.InjectorUtils
import com.daijun.sunflower.practice.viewmodels.PlantListViewModel

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/13
 * @description
 */
class PlantListFragment : Fragment() {

    private val viewModel: PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter

        viewModel.plants.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.filter_zone) {
            updateData()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }
}