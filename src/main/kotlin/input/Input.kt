package input

import command.Command
import logging.Logger
import java.nio.file.Path
import java.nio.file.Paths

class Input {
    companion object {
        fun arguments(args: Array<String>): Pair<Command, Path> {
            val commandName = args[0]
            val commandPath = when (args.count()) {
                2 -> Paths.get(args[1])
                else -> Paths.get(".")
            }

            val command = Command.resolveFromString(commandName)
            if (command == null) {
                Logger.critical("No command found named $commandName", -1)
            }

            return Pair(command as Command, commandPath)
        }
    }
}