package org.jetbrains.plugins.colors_panel.ui

import java.awt.Component
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JTable
import javax.swing.table.TableCellRenderer
class ButtonRendererWithIcon(
    private val iconArg: Icon,
    private val textArg: String? = null // Текст по умолчанию равен null
) : JButton(), TableCellRenderer {

    init {
        isOpaque = true
        this.icon = iconArg
        this.text = textArg ?: ""
        horizontalAlignment = CENTER
    }

    override fun getTableCellRendererComponent(
        table: JTable?,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        setBorder(null)
        background = null
        return this
    }
}
/*
class ButtonRendererWithIcon(private val iconArg: Icon) : JButton(), TableCellRenderer {
    init {
        isOpaque = true
        this.icon = iconArg
    }

    override fun getTableCellRendererComponent(
        table: JTable?,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        setBorder(null)
        background = null
        return this
    }
}*/
