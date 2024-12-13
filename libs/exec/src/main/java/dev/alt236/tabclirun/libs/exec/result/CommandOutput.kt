package dev.alt236.tabclirun.libs.exec.result

import kotlin.time.Duration

data class CommandOutput(
    val command: String,
    val executionDuration: Duration,
    val exitCode: Int,
    val lines: List<Line>,
) {
    val isSuccess = exitCode == 0

    val isOnlyStdOut: Boolean by lazy {
        lines.all { !it.isError }
    }

    val isOnlyStdErr: Boolean by lazy {
        lines.all { it.isError }
    }
}
