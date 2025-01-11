package org.jetbrains.plugins.colors_panel.ui

import com.intellij.ui.ColorPicker
import com.intellij.ui.components.JBPanel
import com.intellij.ui.table.JBTable
import org.jetbrains.plugins.colors_panel.ColorEntry
import java.awt.Color
import java.awt.Component
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JScrollPane

class ColorTablePanel : JBPanel<ColorTablePanel>() {

    private val colorEntries: MutableList<ColorEntry> = mutableListOf()
    private val tableModel = ColorTableModel(colorEntries)


    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        val deleteIcon = ImageIcon(javaClass.getResource("/icons/delete-outline.png"))
        // Таблица
        val table = JBTable(tableModel).apply {
            rowHeight = 30
        }
        table.columnModel.getColumn(0).cellEditor = ColorCellEditor()
        table.columnModel.getColumn(0).cellRenderer = ColorCellRenderer()
        // Настраиваем рендерер и редактор для кнопки "Удалить"
        table.columnModel.getColumn(2).cellRenderer = ButtonRendererWithIcon(deleteIcon)
        table.columnModel.getColumn(2).cellEditor = ButtonEditorWithIcon(deleteIcon) { rowIndex ->
            tableModel.setValueAt(null, rowIndex, 2)
        }
        // Кнопка добавления
        val addButton = JButton("Add Color").apply {
            addActionListener {
                val parent = this.topLevelAncestor as? Component
                val newColor =
                    parent?.let { it1 -> ColorPicker.showDialog(it1, "Choose Color", Color.WHITE, true, null, false) }
                if (newColor != null) {
                    val currentHex = "#%02x%02x%02x".format(
                        newColor.red, newColor.green, newColor.blue
                    )
                    colorEntries.add(ColorEntry(newColor, currentHex))
                    tableModel.fireTableRowsInserted(colorEntries.size - 1, colorEntries.size - 1)
                }
            }
        }

        // Добавление компонентов в панель
        add(JScrollPane(table))
        add(addButton)
    }
    fun loadColors(savedColors: List<ColorEntry>) {
        colorEntries.clear()
        colorEntries.addAll(savedColors)
        tableModel.fireTableDataChanged()
    }
    fun getCurrentColors(): List<ColorEntry> {
        // Возвращаем текущий список цветов из таблицы
        return colorEntries
    }

}



