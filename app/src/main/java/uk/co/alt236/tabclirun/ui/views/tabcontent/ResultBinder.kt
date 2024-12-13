package uk.co.alt236.tabclirun.ui.views.tabcontent

import dev.alt236.tabclirun.libs.exec.result.CommandOutput
import uk.co.alt236.tabclirun.ui.implementations.ColorPane
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.measureTime

internal class ResultBinder(
    private val colorPane: ColorPane,
    private val verbose: Boolean,
) {

    fun bind(colors: CommandColors, commandOutput: CommandOutput) {
        if (verbose) {
            println("LINES : '${commandOutput.command}: ${commandOutput.lines.size}'")
        }

        val factory = DocumentFactory(colors)
        val document: DocumentFactory.DocumentWrapper

        val elapsed = measureTime {
            document = factory.getDocument(commandOutput)
            colorPane.document = document.document
        }

        if (verbose) {
            println("BIND  : '${commandOutput.command}' --> Time: $elapsed <--")
            val debugInfo = getDebugString(commandOutput, elapsed, document.method)
            colorPane.append(colors.textColor, debugInfo)
        }
    }


    private fun getDebugString(commandOutput: CommandOutput, elapsed: Duration, renderMethod: String): String {
        val sb = StringBuilder()
        sb.appendLine("---------------")
        sb.append("Command: '", commandOutput.command, "'", '\n')
        sb.append("Lines  : ", commandOutput.lines.size, '\n')
        sb.append("Method : ", renderMethod, '\n')
        sb.appendLine()
        sb.append("TIME-EXEC: ", commandOutput.executionDuration.pretty(), '\n')
        sb.append("TIME-BIND: ", elapsed.pretty(), '\n')
        return sb.toString()
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