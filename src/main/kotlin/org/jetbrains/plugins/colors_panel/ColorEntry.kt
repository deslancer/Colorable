package org.jetbrains.plugins.colors_panel

import com.intellij.util.xmlb.annotations.Tag

@Tag("ColorEntry")
data class ColorEntry(
    @Tag("color") var color: java.awt.Color  = java.awt.Color.BLACK ,
    @Tag("hex") var hex: String = ""
)