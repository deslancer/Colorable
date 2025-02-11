package org.jetbrains.plugins.colors_panel.actions

import ColorTableDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent



class ShowColorTableAction : AnAction("Colorable") {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        if (project != null) {
            val dialog = ColorTableDialog(project)
            dialog.show()
        }
    }
}