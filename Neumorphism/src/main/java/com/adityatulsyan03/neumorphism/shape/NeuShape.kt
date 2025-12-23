package com.adityatulsyan03.neumorphism.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.adityatulsyan03.neumorphism.NeuStyle

abstract class NeuShape(open val cornerShape: CornerShape) {
    abstract fun draw(drawScope: ContentDrawScope, style: NeuStyle)
}