package com.daijun.sunflower.practice.adapters

import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daijun.sunflower.practice.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/10
 * @description
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, desc: String?) {
    if (desc.isNullOrEmpty()) {
        view.text = ""
        return
    }
    view.text = HtmlCompat.fromHtml(desc, HtmlCompat.FROM_HTML_MODE_COMPACT)
    view.movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter("wateringText")
fun bindWateringText(view: TextView, wateringInterval: Int) {
    view.text = view.resources.getQuantityString(
        R.plurals.watering_needs_suffix,
        wateringInterval,
        wateringInterval
    )
}