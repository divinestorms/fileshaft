package command

import java.nio.file.Path

interface ICommand {
    fun name(): String
    fun apply(path: Path, args: Array<String>)
    fun revert(path: Path, log: Path)
}