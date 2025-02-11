import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.project.Project
import org.jetbrains.plugins.colors_panel.ProjectColorSettings
import org.jetbrains.plugins.colors_panel.ui.ColorTablePanel
import javax.swing.JComponent

class ColorTableDialog(project: Project) : DialogWrapper(project) {

    private val colorTablePanel = ColorTablePanel()
    private val currentProject = project

    init {
        title = "Colorable"
        setOKButtonText("Save")

        //val settings = com.intellij.openapi.application.ApplicationManager.getApplication().getService(ColorSettings::class.java)
        val settings = ProjectColorSettings.getInstance(project)
        val savedColors = settings.getColorEntries()
        colorTablePanel.loadColors(savedColors)
        setSize(500, 350)

        window?.setLocationRelativeTo(null)
        init()

    }

    override fun createCenterPanel(): JComponent {
        return colorTablePanel
    }

    override fun doOKAction() {
        val settings = ProjectColorSettings.getInstance(currentProject)
        settings.saveColorEntries(colorTablePanel.getCurrentColors())

        super.doOKAction()
    }
}
