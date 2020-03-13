package com.daijun.sunflower.practice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.daijun.sunflower.practice.R
import com.daijun.sunflower.practice.adapters.MY_GARDEN_PAGE_INDEX
import com.daijun.sunflower.practice.adapters.PLANT_LIST_PAGE_INDEX
import com.daijun.sunflower.practice.adapters.SunflowerPagerAdatper
import com.daijun.sunflower.practice.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/2/29
 * @description
 */
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        binding.viewpager2.adapter = SunflowerPagerAdatper(this)

        TabLayoutMediator(binding.tabs, binding.viewpager2) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            else -> throw IndexOutOfBoundsException()
        }
    }
}