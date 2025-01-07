package org.jetbrains.plugins.colors_panel.ui

import org.jetbrains.plugins.colors_panel.ColorEntry
import java.awt.Color
import javax.swing.table.AbstractTableModel

class ColorTableModel(private val colorEntries: MutableList<ColorEntry>) : AbstractTableModel() {

    private val columnNames = arrayOf("Color", "HEX", "Remove")

    override fun getRowCount(): Int = colorEntries.size

    override fun getColumnCount(): Int = columnNames.size

    override fun getColumnName(column: Int): String = columnNames[column]

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        return when (columnIndex) {
            0 -> colorEntries[rowIndex].color // Возвращаем только Color
            1 -> colorEntries[rowIndex].hex // Возвращаем HEX
            2 -> "Remove" // Текст для кнопки
            else -> ""
        }
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        // Разрешаем редактирование только для столбцов "Цвет" и "Удалить"
        return columnIndex == 0 || columnIndex == 2
    }

    override fun setValueAt(value: Any?, rowIndex: Int, columnIndex: Int) {
        if (rowIndex in 0 until colorEntries.size) {
            val colorEntry = colorEntries[rowIndex]
            when (columnIndex) {
                0 -> { // Изменение цвета
                    if (value is Color) {
                        colorEntry.color = value
                        colorEntry.hex = "#%02x%02x%02x".format(
                            value.red, value.green, value.blue
                        )
                        fireTableCellUpdated(rowIndex, 0) // Обновляем ячейку цвета
                        fireTableCellUpdated(rowIndex, 1) // Обновляем HEX код
                    }
                }
                2 -> { // Удаление строки
                    colorEntries.removeAt(rowIndex)
                    fireTableRowsDeleted(rowIndex, rowIndex) // Уведомляем таблицу об удалении
                }
            }
        }
    }

    override fun getColumnClass(columnIndex: Int): Class<*> {
        return when (columnIndex) {
            0 -> Color::class.java // Столбец "Цвет"
            1 -> String::class.java // Столбец "HEX"
            2 -> String::class.java // Столбец "Удалить"
            else -> Any::class.java
        }
    }
}

