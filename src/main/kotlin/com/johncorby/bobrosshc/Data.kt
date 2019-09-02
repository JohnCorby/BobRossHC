package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.Main.Companion.PLUGIN
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlin.properties.Delegates

object Data {
    private lateinit var file: File

    lateinit var deadPlayers: MutableList<String>
    var currentSeason by Delegates.notNull<Int>()

    fun load() {
        if (!PLUGIN.dataFolder.exists()) PLUGIN.dataFolder.mkdir()
        file = File(PLUGIN.dataFolder, DATA_FILE_NAME)

        ObjectInputStream(file.inputStream()).use {
            deadPlayers = it.readObject() as MutableList<String>
        }

        PLUGIN.saveDefaultConfig()
        currentSeason = PLUGIN.config.getInt("current-season")
    }

    fun save() {
        ObjectOutputStream(file.outputStream()).use {
            it.writeObject(deadPlayers)
        }

        PLUGIN.config.set("current-season", currentSeason)
        PLUGIN.saveConfig()
    }
}
