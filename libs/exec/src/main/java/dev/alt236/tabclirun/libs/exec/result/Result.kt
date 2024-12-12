package dev.alt236.tabclirun.libs.exec.result

import kotlin.time.Duration

data class Result(
    val command: String,
    val executionDuration: Duration,
    val exitCode: Int,
    val lines: List<Line>,
) {
    val isSuccess = exitCode == 0

    val hasStdErr: Boolean by lazy {
        lines.any { it.isError }
    }
}
