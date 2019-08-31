package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.Main.Companion.PLUGIN
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Config {
    private const val CONFIG_NAME = "playerData.yml"
    private lateinit var file: File
    private lateinit var config: YamlConfiguration

    private var data = mutableMapOf<String, Boolean>()

    fun load() {
//        PLUGIN.dataFolder.mkdir()
        file = File(PLUGIN.dataFolder, CONFIG_NAME)
        config = YamlConfiguration.loadConfiguration(file)

        data = config.getValues(false) as MutableMap<String, Boolean>
    }

    fun save() {
        for ((key, value) in data)
            config[key] = value

        config.save(file)
    }
}
