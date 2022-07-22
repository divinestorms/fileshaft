package command.base

import command.Command
import history.History
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.isSameFileAs

class Revert : Command()  {
    override val name: String = "revert"
    override val handler
        get() = path@{ _: Path ->
            val history = History()

            val changes = history.load()
            for ((new, old) in changes) {
                Files.move(Paths.get(new), Paths.get(old))
            }

            return@path null
        }
    override val filter: (Path) -> Boolean get() = { file -> file.isSameFileAs(History.file) }
}