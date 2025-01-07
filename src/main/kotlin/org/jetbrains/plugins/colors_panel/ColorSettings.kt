import com.intellij.openapi.components.Service
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.plugins.colors_panel.ColorEntry

@State(name = "ColorSettings", storages = [Storage("ColorSettings.xml")])
@Service
class ColorSettings : PersistentStateComponent<ColorSettings.State> {
    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
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