package org.jetbrains.plugins.colors_panel.ui

import ColorSettings
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.ex.ToolWindowManagerListener
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.table.JBTable
import org.jetbrains.plugins.colors_panel.ColorEntry
import org.jetbrains.plugins.colors_panel.ProjectColorSettings
import javax.swing.JPanel
import javax.swing.JButton

import java.awt.BorderLayout
import javax.swing.JScrollPane

class ColorableToolWindowFactory : ToolWindowFactory {
    private val colorEntries: MutableList<ColorEntry> = mutableListOf()
    private val tableModel = ColorTableModel(colorEntries)
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {

        val panel = createPanel()
        project.messageBus.connect().subscribe(ToolWindowManagerListener.TOPIC, object : ToolWindowManagerListener {
            override fun toolWindowShown(toolWindow: ToolWindow) {
                if (toolWindow.id == "Colorable") {
                    println("Colorable ToolWindow opened!")
                    //val settings = com.intellij.openapi.application.ApplicationManager.getApplication().getService(ColorSettings::class.java)
                    val settings = ProjectColorSettings.getInstance(project)
                    val savedColors = settings.getColorEntries()
                    loadColors(savedColors)
                    println(savedColors)
                }
            }
        })
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
    fun loadColors(savedColors: List<ColorEntry>) {
        colorEntries.clear()
        colorEntries.addAll(savedColors)
        tableModel.fireTableDataChanged()
    }
}

