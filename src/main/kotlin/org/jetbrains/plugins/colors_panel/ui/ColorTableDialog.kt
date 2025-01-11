import com.intellij.openapi.ui.DialogWrapper
import org.jetbrains.plugins.colors_panel.ui.ColorTablePanel
import javax.swing.JComponent

class ColorTableDialog : DialogWrapper(true) {

    private val colorTablePanel = ColorTablePanel()

    init {
        title = "Colorable"
        setOKButtonText("Save")

        val settings = com.intellij.openapi.application.ApplicationManager.getApplication().getService(ColorSettings::class.java)
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
        val settings = com.intellij.openapi.application.ApplicationManager.getApplication().getService(ColorSettings::class.java)
        settings.saveColorEntries(colorTablePanel.getCurrentColors())

        super.doOKAction()
    }
}
