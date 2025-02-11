import com.intellij.configurationStore.StreamProvider
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.*
import org.jetbrains.plugins.colors_panel.ColorEntry

/*@State(name = "ColorSettings", storages =  [Storage("\$APP_CONFIG$/colors_panel.xml")])*/
@State(
    name = "ProjectColorSettings",
    storages = [Storage(".idea/color-settings-workspace.xml")]
)
@Service
class ColorSettings : PersistentStateComponent<ColorSettings.State> {
    private var state = State()
    private val storagePath: String
        get() = PathManager.getConfigPath() + "/colors_panel.xml"

    init {
        println("ColorSettings сохранен в: $storagePath")
    }
    override fun getState(): State {
        println("Сохранение состояния: $state")
        return state
    }

    override fun loadState(state: State) {
        println("Загрузка состояния: $state")
        this.state = state
    }

    fun getColorEntries(): List<ColorEntry> = state.colorEntries

    fun saveColorEntries(entries: List<ColorEntry>) {
        state.colorEntries = entries.toMutableList()
    }

    data class State(
        var colorEntries: MutableList<ColorEntry> = mutableListOf()
    )

    companion object {
        val colorEntries: MutableList<ColorEntry> = mutableListOf()
    }
}