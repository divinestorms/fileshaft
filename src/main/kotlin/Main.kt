import command.*


/* Usage:
    fileshaft mess -r
    fileshaft pics -r
   Options:
        -r Revert
 */

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage:\n\tfileshaft mess -r" +
                "\n\tfileshaft pics -r"
        )
        return
    }

    val commands = Commands()
    val commandName = args[0]

    val command = commands.find(commandName)
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