package dev.alt236.tabclirun.libs.exec

import dev.alt236.tabclirun.libs.exec.result.CommandOutput
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.PumpStreamHandler
import kotlin.time.Duration
import kotlin.time.measureTime

class CommandExecutor(private val verbose: Boolean) {

    fun executeCommand(command: String): CommandOutput {
        val collector = OutputCollector()
        val executor = DefaultExecutor.builder().get()
        executor.streamHandler = PumpStreamHandler(collector.outStream, collector.errStream)

        val commandline = CommandLine.parse(command)
        val runner = CommandRunner()

        if (verbose) {
            println("START : '$command'")
        }

        val elapsed: Duration = measureTime {
            runner.run(executor, collector, commandline)
        }

        if (verbose) {
            println("END   : '$command' --> Time: $elapsed <--")
        }

        val internalResult = runner.result

        return CommandOutput(
            command = command,
            executionDuration = elapsed,
            exitCode = internalResult.exitCode,
            lines = internalResult.lines
        )
    }

}
