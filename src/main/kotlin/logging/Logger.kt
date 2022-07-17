package logging

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

class Logger { // ?? logging on println????
    companion object {
        private val messages = mutableListOf<String>()
        private val timeStampFormat = DateTimeFormatter.ofPattern("HH:mm:ss")

        fun log(string: String) {
            val timestamp = LocalDateTime.now().format(timeStampFormat)

            messages.add("[$timestamp] $string")
        }

        fun warn(string: String) {
            val timestamp = LocalDateTime.now().format(timeStampFormat)

            messages.add("[$timestamp] $string (*)")
        }


        fun critical(string: String, exitCode: Int) {
            println(string)
            exitProcess(exitCode)
        }
        fun showMessages() {
            for (message in messages)
                println(message)
        }
    }
}