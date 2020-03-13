package com.daijun.sunflower.practice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.daijun.sunflower.practice.R
import com.daijun.sunflower.practice.adapters.GardenPlantingAdater
import com.daijun.sunflower.practice.adapters.MY_GARDEN_PAGE_INDEX
import com.daijun.sunflower.practice.adapters.PLANT_LIST_PAGE_INDEX
import com.daijun.sunflower.practice.databinding.FragmentGardenBinding
import com.daijun.sunflower.practice.utilities.InjectorUtils
import com.daijun.sunflower.practice.viewmodels.GardenPlantingListViewModel
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/9
 * @description
 */
class GardenFragment : Fragment() {

    private val viewModel: GardenPlantingListViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = GardenPlantingAdater()
        binding.gardenList.adapter = adapter
        binding.addPlant.setOnClickListener {
            navigateToPlantListPage()
        }
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: GardenPlantingAdater, binding: FragmentGardenBinding) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) {
            binding.hasPlantings = !it.isNullOrEmpty()
            adapter.submitList(it)
        }
    }

    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.viewpager2).currentItem =
            PLANT_LIST_PAGE_INDEX
    }
}