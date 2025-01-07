package org.jetbrains.plugins.colors_panel.ui


import com.intellij.ui.ColorPicker
import java.awt.Color
import java.awt.Component
import javax.swing.AbstractCellEditor
import javax.swing.JButton
import javax.swing.JTable
import javax.swing.table.TableCellEditor

class ColorCellEditor : AbstractCellEditor(), TableCellEditor {
    private val button = JButton()
    private var currentColor: Color = Color.WHITE

    init {
        button.addActionListener {
            val parent = button.topLevelAncestor as? Component
            val newColor =
                parent?.let { it1 -> ColorPicker.showDialog(it1, "Choose Color", currentColor, true, null, false) }
            if (newColor != null) {
                currentColor = newColor
                fireEditingStopped() // Уведомляем таблицу об изменении
            }
        }
    }

    override fun getCellEditorValue(): Any = currentColor

    override fun getTableCellEditorComponent(
        table: JTable,
        value: Any?,
        isSelected: Boolean,
        row: Int,
        column: Int
    ): Component {
        if (value is Color) {
            currentColor = value
            button.background = currentColor
        }
        return button
    }
}