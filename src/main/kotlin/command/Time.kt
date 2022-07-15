package command

import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.nameWithoutExtension

class Time : ICommand {
    override fun name(): String = "pics"

    override fun apply(path: Path, args: Array<String>) {
        val files = Files.list(path)
            .filter {
                    file -> file.isRegularFile() &&
                    !file.endsWith(".jar") &&
                    !file.endsWith(".exe")
            }
        for (file in files) {
            val fileBody = file.nameWithoutExtension
                .filter { char -> char.isDigit() }

            val rawFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss")

            try {
                val date = LocalDateTime.parse(fileBody, rawFormatter)
                println("$fileBody -> $date")

                val fileName = date.format(formatter) + "." + file.extension

                val newName = file.resolveSibling(fileName)
                Files.move(file, newName)


            } catch (e: DateTimeParseException) {
                println("${e.message}: $file")
            }
        }

    }

    override fun revert(path: Path, log: Path) {
        TODO("Not yet implemented")
    }

}