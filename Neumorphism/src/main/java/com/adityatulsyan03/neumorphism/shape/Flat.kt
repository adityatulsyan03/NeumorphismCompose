package com.adityatulsyan03.neumorphism.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.adityatulsyan03.neumorphism.NeuStyle
import com.adityatulsyan03.neumorphism.drawBackgroundShadows

class Flat(override val cornerShape: CornerShape) : NeuShape(cornerShape) {
    override fun draw(drawScope: ContentDrawScope, style: NeuStyle) {
        drawScope.drawBackgroundShadows(this, style)
        drawScope.drawContent()
    }
}