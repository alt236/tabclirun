package dev.alt236.tabclirun.libs.exec.result

internal data class InternalResult(
    val exitCode: Int,
    val lines: List<Line>,
)
