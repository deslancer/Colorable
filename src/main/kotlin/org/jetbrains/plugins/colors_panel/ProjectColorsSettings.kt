package org.jetbrains.plugins.colors_panel

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.util.xmlb.annotations.XCollection


@State(
    name = "ProjectColorSettings",
    storages = [Storage("colorScheme.xml")]
)
class ProjectColorSettings : PersistentStateComponent<ProjectColorSettings> {
    @XCollection(style = XCollection.Style.v2)
    private var _colorEntries: MutableList<ColorEntry> = mutableListOf()

    override fun getState(): ProjectColorSettings = this

    override fun loadState(state: ProjectColorSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    fun getColorEntries(): List<ColorEntry> = _colorEntries

    fun saveColorEntries(entries: List<ColorEntry>) {
        _colorEntries = entries.toMutableList()
    }

    companion object {
        fun getInstance(project: Project): ProjectColorSettings =
            project.getService(ProjectColorSettings::class.java)
    }
}
