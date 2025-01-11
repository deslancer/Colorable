package org.jetbrains.plugins.colors_panel.ui

import java.awt.Component
import javax.swing.AbstractCellEditor
import javax.swing.JButton
import javax.swing.JTable
import javax.swing.table.TableCellEditor

class ButtonEditor(private val onClick: (Int) -> Unit, private val tableModel: ColorTableModel) : AbstractCellEditor(),
    TableCellEditor {
    private val button = JButton("Remove").apply {
        addActionListener {
            onClick.invoke(row)
            stopCellEditing()
            tableModel.fireTableDataChanged()
        }
    }

    private var row: Int = -1

    override fun getCellEditorValue(): Any = "Remove"

    override fun getTableCellEditorComponent(
        table: JTable,
        value: Any?,
        isSelected: Boolean,
        row: Int,
        column: Int
    ): Component {
        this.row = row
        return button
    }
}