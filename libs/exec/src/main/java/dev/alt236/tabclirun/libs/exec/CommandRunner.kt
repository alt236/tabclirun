package dev.alt236.tabclirun.libs.exec

import dev.alt236.tabclirun.libs.exec.result.InternalResult
import dev.alt236.tabclirun.libs.exec.result.Line
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import java.io.PrintWriter
import java.io.StringWriter

internal class CommandRunner {
    private var _result: InternalResult? = null

    val result: InternalResult
        get() = _result ?: error("Command runner has no result! Have you called 'run'?")

    fun run(
        executor: DefaultExecutor,
        collector: OutputCollector,
        commandline: CommandLine,
    ) {
        _result = try {
            val exitCode = executor.execute(commandline)
            val lines = collector.lines

            InternalResult(exitCode, lines)
        } catch (e: Exception) {
            e.toResult()
        }
    }


    private fun Exception.toResult(): InternalResult {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        this.printStackTrace(pw)

        val stackTrace = sw.toString() // stack trace as a string
        val stringLines = listOf(*stackTrace.split("\\r?\\n".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray())

        val lines = stringLines.map { Line(it, true) }
        return InternalResult(-1, lines)
    }
}