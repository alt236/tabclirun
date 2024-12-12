package uk.co.alt236.tabclirun.ui.views.tabcontent

import dev.alt236.tabclirun.libs.exec.result.Result
import uk.co.alt236.tabclirun.ui.implementations.ColorPane
import kotlin.time.measureTime

internal class ResultBinder(
    private val colorPane: ColorPane,
    private val verbose: Boolean,
) {

    fun bind(colors: CommandColors, result: Result) {
        if (verbose) {
            println("LINES : '${result.command}: ${result.lines.size}'")
        }

        val elapsed = measureTime {
            if (result.hasStdErr) {
                for (line in result.lines) {
                    val color = colors.getColor(line)
                    colorPane.append(color, line.text + "\n")
                }
            } else {
                val textColor = colors.textColor

                val sb = StringBuilder()
                for (line in result.lines) {
                    sb.append(line.text)
                    sb.append('\n')
                }

                colorPane.append(textColor, sb.toString())
            }
        }

        if (verbose) {
            println("BIND  : '${result.command}' --> Time: $elapsed <--")
        }
    }

}