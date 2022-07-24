package command

import history.History
import logging.Logger
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.streams.toList

const val pkg = "command.base"
abstract class Command {
    protected val history = History()
    abstract val name: String
    abstract val handler: (file: Path) -> Path? // Last argument for new filename
    abstract val filter: (Path) -> Boolean

    open fun apply(path: Path) {
        val files = Files.list(path).filter { file -> filter(file) }.toList()

        if (files.isEmpty())
            Logger.critical("No files given in path $path", -2)

        for (file in files) {
            val newName = handler(file) ?: continue

            history.add(file, newName)
            Files.move(file, newName, StandardCopyOption.REPLACE_EXISTING)
            Logger.log("$file -> $newName")
        }
        history.save()
    }

    companion object {
        fun resolveFromString(commandName: String): Command? {
            val commandClass = Class.forName("$pkg.${commandName.replaceFirstChar { it.uppercase() }}").kotlin

            return commandClass.java.getDeclaredConstructor().newInstance() as Command?
        }
    }

}