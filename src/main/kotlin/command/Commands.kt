package command

import command.base.*

class Commands {
    companion object {
        private val commands = arrayOf(Mess(), Time(), Revert())

        fun find(name: String): Command? = commands.firstOrNull { it.name == name }
    }

}