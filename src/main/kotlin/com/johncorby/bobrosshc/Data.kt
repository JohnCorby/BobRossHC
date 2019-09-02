package com.johncorby.bobrosshc

import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlin.properties.Delegates

private const val NAME = "deadPlayers.dat"

private lateinit var dataFile: File

lateinit var deadPlayers: MutableList<String>
var currentSeason by Delegates.notNull<Int>()

private fun File.create() = apply {
    if (isFile) {
        parentFile.mkdirs()
        createNewFile()
    } else mkdirs()
}

private fun <T> File.readObject(): T = ObjectInputStream(inputStream()).use { it.readObject() as T }
private fun File.writeObject(obj: Any) = ObjectOutputStream(outputStream()).use { it.writeObject(obj) }


fun loadData() {
    dataFile = File(PLUGIN.dataFolder, NAME).create()
    deadPlayers = dataFile.readObject()

    PLUGIN.saveDefaultConfig()
    currentSeason = PLUGIN.config.getInt("current-season")
}

fun saveData() {
    dataFile.writeObject(deadPlayers)

    PLUGIN.config.set("current-season", currentSeason)
    PLUGIN.saveConfig()
}
