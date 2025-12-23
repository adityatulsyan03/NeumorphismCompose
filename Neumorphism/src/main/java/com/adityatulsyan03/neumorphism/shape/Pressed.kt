package com.adityatulsyan03.neumorphism.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.adityatulsyan03.neumorphism.NeuStyle
import com.adityatulsyan03.neumorphism.drawForegroundShadows

class Pressed(override val cornerShape: CornerShape) : NeuShape(cornerShape) {
    override fun draw(drawScope: ContentDrawScope, style: NeuStyle) {
        drawScope.drawContent()
        drawScope.drawForegroundShadows(this, style)
    }
}