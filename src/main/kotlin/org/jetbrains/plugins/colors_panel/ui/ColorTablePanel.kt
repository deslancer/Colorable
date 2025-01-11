package org.jetbrains.plugins.colors_panel.ui

import com.intellij.openapi.util.IconLoader
import com.intellij.ui.ColorPicker
import com.intellij.ui.components.JBPanel
import com.intellij.ui.table.JBTable
import org.jetbrains.plugins.colors_panel.ColorEntry
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JScrollPane

class ColorTablePanel : JBPanel<ColorTablePanel>() {

    private val colorEntries: MutableList<ColorEntry> = mutableListOf()
    private val tableModel = ColorTableModel(colorEntries)


    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        val deleteIcon = IconLoader.getIcon("/icons/dark/delete_dark.svg", javaClass)
        val addIcon = IconLoader.getIcon("/icons/dark/add_dark.svg", javaClass)

        val table = JBTable(tableModel).apply {
            rowHeight = 30
        }
        table.columnModel.getColumn(0).cellEditor = ColorCellEditor()
        table.columnModel.getColumn(0).cellRenderer = ColorCellRenderer()

        table.columnModel.getColumn(1).cellRenderer = TextCellRenderer()
        table.columnModel.getColumn(1).cellEditor = TextCellEditor{ rowIndex ->
            tableModel.setValueAt(null, rowIndex, 1)
        }

        table.columnModel.getColumn(2).cellRenderer = ButtonRendererWithIcon(deleteIcon )
        table.columnModel.getColumn(2).cellEditor = ButtonEditorWithIcon(deleteIcon) { rowIndex ->
            tableModel.setValueAt(null, rowIndex, 2)
        }
        val addBtn = ButtonRendererWithIcon(addIcon, "Add Color")
        addBtn.apply {
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = Dimension(125, 45)
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

        add(JScrollPane(table))
        add(Box.createRigidArea(Dimension(0, 10)))
        add(addBtn)
    }

    fun loadColors(savedColors: List<ColorEntry>) {
        colorEntries.clear()
        colorEntries.addAll(savedColors)
        tableModel.fireTableDataChanged()
    }

    fun getCurrentColors(): List<ColorEntry> {
        return colorEntries
    }

}



