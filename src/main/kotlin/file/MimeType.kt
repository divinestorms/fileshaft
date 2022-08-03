package file

import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile

class MimeType {
    companion object {
        fun isPicture(path: Path): Boolean {
            return isFile(path) &&
                    path.extension == "png" ||
                    path.extension == "jpg" ||
                    path.extension == "jpeg" ||
                    path.extension == "webp" ||
                    path.extension == "gif"
        }

        fun isVideo(path: Path): Boolean {
            return isFile(path) &&
                    path.extension == "mp4"
        }

        private fun isFile(path: Path): Boolean {
            return path.isRegularFile() && !path.isDirectory()
        }
    }
}