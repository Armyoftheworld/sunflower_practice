package com.daijun.sunflower.practice.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daijun.sunflower.practice.fragments.GardenFragment
import com.daijun.sunflower.practice.fragments.PlantListFragment
import java.lang.IndexOutOfBoundsException

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/7
 * @description
 */

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

class SunflowerPagerAdatper(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators = mapOf(
        MY_GARDEN_PAGE_INDEX to { GardenFragment() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}