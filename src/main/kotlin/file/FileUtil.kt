package file

import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile

class FileUtil {
    companion object {
        fun isPicture(path: Path): Boolean {
            return path.isRegularFile() && !path.isDirectory() &&
                    path.extension == "png" ||
                    path.extension == "jpg" ||
                    path.extension == "jpeg" ||
                    path.extension == "gif"
        }
    }
}