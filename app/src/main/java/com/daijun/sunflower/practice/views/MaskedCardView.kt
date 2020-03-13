package com.daijun.sunflower.practice.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import com.daijun.sunflower.practice.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description A Card view that clips the content of any shape, this should be done upstream in card,
 * working around it for now.
 */
class MaskedCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = R.attr.materialCardViewStyle
) : MaterialCardView(context, attributeSet, defStyle) {

    private val pathProvider = ShapeAppearancePathProvider()
    private val path = Path()
    private val shapeAppearance = ShapeAppearanceModel.builder(
        context, attributeSet, defStyle, R.style.Widget_MaterialComponents_CardView
    ).build()
    private val rectF = RectF(0F, 0F, 0F, 0F)

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(path)
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectF.right = w.toFloat()
        rectF.bottom = h.toFloat()
        pathProvider.calculatePath(shapeAppearance, 1F, rectF, path)
        super.onSizeChanged(w, h, oldw, oldh)
    }
}