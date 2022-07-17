package storage

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun makeStamp(path: Path) {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss")
    val dateTime = LocalDateTime.now().format(formatter)

    val directory = Paths.get(path.toString(), "!stamp__$dateTime")
    Files.createDirectory(directory)

    Files.list(path).filter(Files::isRegularFile)
        .forEach { source ->
        val destination = Paths.get(
            directory.toString(),
            source.toString().substring(path.toString().length)
        )
        Files.copy(source, destination)
    }
}