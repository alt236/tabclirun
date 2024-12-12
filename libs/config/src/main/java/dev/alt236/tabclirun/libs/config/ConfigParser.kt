package dev.alt236.tabclirun.libs.config

import dev.alt236.tabclirun.libs.config.internal.parser.JsonParser
import java.io.File

class ConfigParser {
    fun parse(file: File, target: String): List<Command> {
        val parser = JsonParser()
        return parser.parse(file, target)
    }
}