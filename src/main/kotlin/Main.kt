import command.*


/* Usage:
    fileshaft mess -r
    fileshaft pics -r
   Options:
        -r Revert
 */

fun findCommand(commands: Array<ICommand>,name: String): ICommand? {
    for (command in commands) {
        if (command.name() == name) {
            return command
        }
    }
    return null
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage:\n\tfileshaft mess -r" +
                "\n\tfileshaft pics -r"
        )
        return
    }

    val commandName = args[0]
    val commands = arrayOf(Mess(), Time())

    val command = findCommand(commands, commandName)
    if (command == null) {
        println("No command found named $commandName")
        return
    }

    if (args.size == 1) {
        command.apply()
        return
    }
    if (args.contains("-r") && args.size == 2) {
        command.revert()
        return
    }
}