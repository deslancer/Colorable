package org.jetbrains.plugins.colors_panel.ui

import java.awt.Component
import javax.swing.AbstractCellEditor
import javax.swing.JButton
import javax.swing.JTable
import javax.swing.table.TableCellEditor

class TextCellEditor( private val onClick: (Int) -> Unit) :
    AbstractCellEditor(), TableCellEditor {
    private val button = JButton()
    init {
        button.isOpaque = true
        button.addActionListener {
            val table = button.topLevelAncestor as? JTable
            val row = table?.editingRow ?: -1
            if (row != -1) {
                onClick(row)
            }
            fireEditingStopped()
        }
    }
    override fun getCellEditorValue(): Any {
        return this
    }

    override fun getTableCellEditorComponent(
        table: JTable?,
        value: Any?,
        isSelected: Boolean,
        row: Int,
        column: Int
    ): Component {
        return button
    }
}