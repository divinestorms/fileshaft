package command

import storage.ChangeList
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.nameWithoutExtension

class Time : ICommand {
    private val changeListFileName get() = Paths.get(".time.old")
    override fun name(): String = "pics"

    override fun apply() {
        val path = Paths.get(".")
        val files = Files.list(path)
            .filter {
                    file -> file.isRegularFile() &&
                    !file.endsWith(".jar") &&
                    !file.endsWith(".exe")
            }


        val changeList = ChangeList(changeListFileName)
        for (file in files) {
            val fileBody = file.nameWithoutExtension // Clear all IMG_ and other garbage from picture name
                .filter { char -> char.isDigit() }

            val clearText = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss")

            try {
                val date = LocalDateTime.parse(fileBody, clearText)

                val fileName = "${date.format(formatter)}.${file.extension}"
                val formatted = file.resolveSibling(fileName)

                changeList.add(formatted, file)
                Files.move(file, formatted)
            } catch (e: DateTimeParseException) {
                println("${e.message}: $file")
            }
        }

        changeList.write()
    }

    override fun revert() {
        val changeList = ChangeList(changeListFileName)
        val changes = changeList.read()

        for ((dst, src) in changes) {
            Files.move(Paths.get(dst), Paths.get(src))
        }
    }

}