package uk.co.alt236.tabclirun.ui.views.tabcontent

import dev.alt236.tabclirun.libs.config.Command
import dev.alt236.tabclirun.libs.exec.result.Line
import java.awt.Color

internal class CommandColors(command: Command) {

    private val errorColor: Color = if (command.errorColor == null) COLOR_ERR
    else command.errorColor

    private val textColor: Color = if (command.textColor == null) COLOR_STD
    else command.textColor

    fun getColor(line: Line): Color = if (line.isError) {
        errorColor
    } else {
        textColor
    }

    private companion object {
        private val COLOR_ERR: Color = Color.decode("#E94F64")
        private val COLOR_STD: Color = Color.WHITE
    }
}
