package org.jetbrains.plugins.colors_panel.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.table.JBTable
import org.jetbrains.plugins.colors_panel.ColorEntry
import javax.swing.JPanel
import javax.swing.JButton

import java.awt.BorderLayout
import javax.swing.JScrollPane

class ColorableToolWindowFactory : ToolWindowFactory {
    private val colorEntries: MutableList<ColorEntry> = mutableListOf()
    private val tableModel = ColorTableModel(colorEntries)
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {

        val panel = createPanel()

        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, null, false)
        toolWindow.contentManager.addContent(content)
    }

    private fun createPanel(): JPanel {
        return JPanel(BorderLayout()).apply {

            val table = JBTable(tableModel).apply {
                rowHeight = 30
            }
            table.columnModel.getColumn(0).cellEditor = ColorCellEditor()
            table.columnModel.getColumn(0).cellRenderer = ColorCellRenderer()

            table.columnModel.getColumn(1).cellRenderer = TextCellRenderer()
            table.columnModel.getColumn(1).cellEditor = TextCellEditor{ rowIndex ->
                tableModel.setValueAt(null, rowIndex, 1)
            }
            add(JScrollPane(table))
           /* add(JButton("Add Color").apply {
                addActionListener {
                    println("Add Color clicked!")
                }
            }, BorderLayout.CENTER)*/
        }
    }
}

