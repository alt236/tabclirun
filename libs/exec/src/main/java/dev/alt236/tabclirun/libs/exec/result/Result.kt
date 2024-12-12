package dev.alt236.tabclirun.libs.exec.result

data class Result(
    val exitCode: Int,
    val lines: List<Line>,
)
