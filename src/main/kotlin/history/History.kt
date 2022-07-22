package history

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class History {

    private val changes = mutableMapOf<String, String>()

    fun add(old: Path, new: Path) {
        changes[new.toString()] = old.toString()
    }

    fun save() {
        val json = JSONObject(changes as Map<String, Any>?)

        Files.write(file, json.toString().toByteArray())
    }


    fun load(): MutableMap<String, String> {
        val string = Files.readString(file)
        val json = JSON.parseObject(string)

        changes.clear()
        for ((new, old) in json)
            changes[new] = old as String

        return changes
    }

    companion object {
        val file: Path get() = Paths.get(".fileshaft.json")
    }
}