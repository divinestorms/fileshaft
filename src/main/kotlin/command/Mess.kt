package command

import storage.ChangeList
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.random.Random

class Mess : ICommand {
    private val changeListFileName get() = Paths.get(".mess.old")
    private val defaultValue = 16286684454590L
    private var value = 0L

    override fun name(): String = "mess"

    override fun apply() {
        val path = Paths.get(".")
        val files = Files.list(path)
            .filter {
                    file -> file.isRegularFile() &&
                    !file.endsWith(".jar") &&
                    !file.endsWith(".exe") &&
                    !file.contains(changeListFileName)
            }

        val changeList = ChangeList(changeListFileName)
        for (file in files) {
            val fileName = "${randomNumber()}.${file.extension}"
            val formatted = file.resolveSibling(fileName)

            changeList.add(formatted, file)
            Files.move(file, formatted)
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

    private fun randomNumber(): String {
        val random = Random(System.currentTimeMillis())
        value += random.nextLong(defaultValue) + defaultValue

        return value.toString()
    }
}