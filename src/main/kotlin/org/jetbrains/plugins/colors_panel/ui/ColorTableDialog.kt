import com.intellij.openapi.ui.DialogWrapper
import org.jetbrains.plugins.colors_panel.ui.ColorTablePanel
import javax.swing.JComponent

class ColorTableDialog : DialogWrapper(true) {

    private val colorTablePanel = ColorTablePanel()

    init {
        title = "Colorable"
        setOKButtonText("Save") // Переименовываем кнопку
        // Загрузка сохранённых цветов
        val settings = com.intellij.openapi.application.ApplicationManager.getApplication().getService(ColorSettings::class.java)
        val savedColors = settings.getColorEntries()
        colorTablePanel.loadColors(savedColors) // Подгружаем данные в панель
        setSize(500, 350)
        // (Опционально) Центрируем окно на экране
        window?.setLocationRelativeTo(null)
        init() // Инициализация диалога

    }

    override fun createCenterPanel(): JComponent {
        return colorTablePanel // Вставляем вашу панель
    }

    override fun doOKAction() {
        // Сохраняем выбранные цвета
        val settings = com.intellij.openapi.application.ApplicationManager.getApplication().getService(ColorSettings::class.java)
        settings.saveColorEntries(colorTablePanel.getCurrentColors())

        super.doOKAction() // Закрываем диалог
    }
}
