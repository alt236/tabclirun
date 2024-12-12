package uk.co.alt236.tabclirun.ui.views.tabcontent

import dev.alt236.tabclirun.libs.exec.result.Result
import uk.co.alt236.tabclirun.ui.implementations.ColorPane
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
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
                    sb.appendLine(line.text)
                }

                colorPane.append(textColor, sb.toString())
            }
        }

        if (verbose) {
            println("BIND  : '${result.command}' --> Time: $elapsed <--")

            val sb = StringBuilder()
            sb.appendLine("---------------")
            sb.append("Command: '", result.command, "'", '\n')
            sb.append("Lines  : ", result.lines.size, '\n')
            sb.appendLine()
            sb.append("TIME-EXEC: ", result.executionDuration.pretty(), '\n')
            sb.append("TIME-BIND: ", elapsed.pretty(), '\n')

            colorPane.append(colors.textColor, sb.toString())
        }
    }

    private fun Duration.pretty(): String {
        return this.toComponents { hours, minutes, seconds, nanoseconds ->
            val fHours = hours.toString().padStart(2, '0')
            val fMin = minutes.toString().padStart(2, '0')
            val fSec = seconds.toString().padStart(2, '0')
            val fMillis = TimeUnit.NANOSECONDS.toMillis(nanoseconds.toLong())
                .toString().padStart(3, '0')

            "${fHours}h ${fMin}m ${fSec}s ${fMillis}ms"
        }
    }


}