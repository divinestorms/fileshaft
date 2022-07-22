package command

import logging.Logger
import storage.makeStamp
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile
import kotlin.streams.toList

abstract class Command {
    abstract val name: String
    abstract val handler: (file: Path) -> Path? // Last argument for new filename
    abstract val filter: (Path) -> Boolean

    fun apply(path: Path) {
        val files = Files.list(path).filter { file -> filter(file) }.toList()

        if (files.isEmpty())
            Logger.critical("No files given in path $path", -2)

        makeStamp(path)

        for (file in files) {
            val newFileName = handler(file) ?: continue

            Files.move(file, newFileName)
            Logger.log("$file -> $newFileName")
        }
    }

    protected fun isPicture(file: Path): Boolean {
        return file.isRegularFile() && !file.isDirectory() && (
                file.extension == "png" ||
                file.extension == "jpg" ||
                file.extension == "jpeg" ||
                file.extension == "gif")
    }
}