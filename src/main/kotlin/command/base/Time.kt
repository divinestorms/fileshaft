package command.base

import command.Command
import logging.Logger
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.io.path.extension
import kotlin.io.path.nameWithoutExtension

class Time : Command() {
    override val name: String = "time"
    override val filter: (Path) -> Boolean get() = { file -> isPicture(file) }

    override val handler
        get() =
            path@{ file: Path ->
                val fileBody = file.nameWithoutExtension.filter { char -> char.isDigit() }

                val cleared = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss")

                val date: LocalDateTime = try {
                    LocalDateTime.parse(fileBody, cleared)
                } catch (e: DateTimeParseException) {
                    Logger.warn("Incorrect format, skipping: $file")
                    null
                } ?: return@path null

                val newFileName = "${date.format(formatter)}.${file.extension}"
                return@path file.resolveSibling(newFileName)
            }
}