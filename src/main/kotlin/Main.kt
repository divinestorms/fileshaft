import input.Input
import logging.Logger

/* Usage:
    fileshaft mess <path>
    fileshaft pics <path>

    If no path given, gets current directory
 */
fun main(args: Array<String>) {
    if (args.isEmpty())
        Logger.critical("Usage:\n\tfileshaft mess <path>\n\tfileshaft pics <path>", 0)


    val (command, path ) = Input.arguments(args)
    command.apply(path)

    Logger.showMessages()
}