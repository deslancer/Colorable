package org.jetbrains.plugins.colors_panel.ui

import org.jetbrains.plugins.colors_panel.ColorEntry
import java.awt.Color
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.datatransfer.Transferable
import javax.swing.table.AbstractTableModel

class ColorTableModel(private val colorEntries: MutableList<ColorEntry>) : AbstractTableModel() {

    private val columnNames = arrayOf("Color", "HEX", "Remove")

    override fun getRowCount(): Int = colorEntries.size

    override fun getColumnCount(): Int = columnNames.size

    override fun getColumnName(column: Int): String = columnNames[column]

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        return when (columnIndex) {
            0 -> colorEntries[rowIndex].color
            1 -> colorEntries[rowIndex].hex
            2 -> "Remove"
            else -> ""
        }
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        return columnIndex == 0 || columnIndex == 1 || columnIndex == 2
    }

    override fun setValueAt(value: Any?, rowIndex: Int, columnIndex: Int) {
        if (rowIndex in 0 until colorEntries.size) {
            val colorEntry = colorEntries[rowIndex]
            when (columnIndex) {
                0 -> {
                    if (value is Color) {
                        colorEntry.color = value
                        colorEntry.hex = "#%02x%02x%02x".format(
                            value.red, value.green, value.blue
                        )
                        fireTableCellUpdated(rowIndex, 0)
                        fireTableCellUpdated(rowIndex, 1)
                    }
                }
                1 -> {
                    val hexValue = colorEntry.hex
                    val clipboard: Transferable = StringSelection(hexValue)
                    Toolkit.getDefaultToolkit().systemClipboard.setContents(clipboard, null)
                }
                2 -> {
                    colorEntries.removeAt(rowIndex)
                    fireTableRowsDeleted(rowIndex, rowIndex)
                }
            }
        }
    }

    override fun getColumnClass(columnIndex: Int): Class<*> {
        return when (columnIndex) {
            0 -> Color::class.java
            1 -> String::class.java
            2 -> String::class.java
            else -> Any::class.java
        }
    }
}

