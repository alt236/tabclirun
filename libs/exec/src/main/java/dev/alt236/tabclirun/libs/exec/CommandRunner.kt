package dev.alt236.tabclirun.libs.exec

import dev.alt236.tabclirun.libs.exec.result.Line
import dev.alt236.tabclirun.libs.exec.result.Result
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import java.io.PrintWriter
import java.io.StringWriter

internal class CommandRunner {
    private var _result: Result? = null

    val result: Result
        get() = _result ?: error("Command runner has no result! Have you called 'run'?")

    fun run(
        executor: DefaultExecutor,
        collector: OutputCollector,
        commandline: CommandLine,
        rawCommand: String,
    ) {
        _result = try {
            val exitCode = executor.execute(commandline)
            val lines = collector.lines

            Result(command = rawCommand, exitCode, lines)
        } catch (e: Exception) {
            e.toResult(rawCommand)
        }
    }


    private fun Exception.toResult(rawCommand: String): Result {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        this.printStackTrace(pw)

        val stackTrace = sw.toString() // stack trace as a string
        val stringLines = listOf(*stackTrace.split("\\r?\\n".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray())

        val lines = stringLines.map { Line(it, true) }
        return Result(rawCommand, -1, lines)
    }
}