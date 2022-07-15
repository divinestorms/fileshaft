package command

import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.Properties
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.nameWithoutExtension
import kotlin.random.Random

class Mess : ICommand {
    private val defaultValue = 16286684454590L
    private var value = 0L

    override fun name(): String = "mess"

    override fun apply(path: Path, args: Array<String>) {
        val files = Files.list(path)
            .filter {
                    file -> file.isRegularFile() &&
                    !file.nameWithoutExtension
                        .matches("^\\d+$".toRegex()) &&
                    !file.endsWith(".jar") &&
                    !file.endsWith(".exe")
            }

        val log = HashMap<String, String>()
        val props = Properties()

        for (file in files) {
            val numeric = "${randomNumber()}.${file.extension}"


            val newName = file.resolveSibling(numeric)
            log[file.toString()] = newName.toString()
            Files.move(file, newName)
            println(file)
        }

        for (entry in log.entries) {
            props[entry.key] = entry.value
        }
        props.store(FileOutputStream(".mess.old"), null)
    }

    override fun revert(path: Path, log: Path) {
        TODO("Not yet implemented")
    }

    private fun randomNumber(): String {
        val random = Random(System.currentTimeMillis())
        value += random.nextLong(defaultValue) + defaultValue

        return value.toString()
    }
}