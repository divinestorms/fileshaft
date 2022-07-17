package command.base

import command.Command
import java.nio.file.Path
import java.util.*
import kotlin.io.path.extension

class Mess : Command() { // 12 symbols
    override val name: String = "mess"
    override val handler
        get() = path@{ file: Path ->
            val fileName = "${randomHash}.${file.extension}"

            return@path file.resolveSibling(fileName)
        }
    override val filter: (Path) -> Boolean get() = { file -> isPicture(file) }


    private val hashLength = 13
    private val randomHash: String
        get() {
            val uuid = UUID.randomUUID().toString()
            return uuid.substring(0, hashLength)
        }
}