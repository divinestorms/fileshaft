package command

interface ICommand {
    fun name(): String
    fun apply()
    fun revert()
}