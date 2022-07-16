package storage

import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Path
import java.util.Properties

class ChangeList(private val path: Path) {   // New     Old
    private val changes = hashMapOf<String, String>()

    fun add(dst: Path, src: Path) {
        changes[dst.toString()] = src.toString()
    }

    fun write() {
        val props = Properties()

        changes.forEach {(dst, src) -> props[dst] = src}

        props.store(FileOutputStream(path.toString()), null)
    }

    fun read(): HashMap<String, String> {
        val props = Properties()

        props.load(FileInputStream(path.toFile()))

        props.forEach {(dst, src) -> run {
                changes[dst as String] = src as String
            }
        }

        return changes
    }
}