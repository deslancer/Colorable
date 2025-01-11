package org.jetbrains.plugins.colors_panel.ui

import java.awt.Color
import java.awt.Component
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.table.TableCellRenderer

class ColorCellRenderer : TableCellRenderer {
    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        val panel = JPanel()
        panel.isOpaque = true
        if (value is Color) {
            panel.background = value
        }
        return panel
    }
}