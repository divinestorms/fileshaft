package command

class Commands {
    private val commands = arrayOf(Mess(), Time())

    fun find(name: String): ICommand? {
        for (command in commands)
            if (command.name() == name)
                return command
        return null
    }
}