package com.daijun.sunflower.practice.adapters

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/11
 * @description
 */

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}