package command.base

import command.Command
import file.MimeType
import logging.Logger
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.imageio.ImageIO
import kotlin.io.path.extension
import kotlin.io.path.nameWithoutExtension

class ConvertPic : Command() {
    override val name: String = "convertPic"
    override val handler
        get() = path@{ file: Path ->
            Logger.log("Converting $file...")
            val image = ImageIO.read(file.toFile())

            val convertedName = "${file.nameWithoutExtension}.$outputExtension"
            val isWritten = ImageIO.write(image, outputExtension, Paths.get(convertedName).toFile())

            if (!isWritten) {
                Logger.warn("Failed to write image: $file")
                return@path null
            }

            Files.delete(file)
            return@path null
        }
    override val filter: (Path) -> Boolean get() = { file -> MimeType.isPicture(file) && file.extension != "gif" }

    private val outputExtension: String = "png"
}