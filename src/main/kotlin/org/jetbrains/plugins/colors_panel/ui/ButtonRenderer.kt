package org.jetbrains.plugins.colors_panel.ui

import java.awt.Component
import javax.swing.JButton
import javax.swing.JTable
import javax.swing.table.TableCellRenderer

// Рендерер для кнопки
class ButtonRenderer : JButton(), TableCellRenderer {
    init {
        text = "Remove"
    }

    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        return this
    }
}