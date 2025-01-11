package org.jetbrains.plugins.colors_panel.ui

import java.awt.Component
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JTable
import javax.swing.table.TableCellRenderer

class TextCellRenderer(): JButton(), TableCellRenderer {
    init {
        isOpaque = true
    }
    override fun getTableCellRendererComponent(
        table: JTable?,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        this.text = value.toString()
        setBorder(null)
        background = null
        if (!isSelected) {
            background = null
        }
        return this
    }
}