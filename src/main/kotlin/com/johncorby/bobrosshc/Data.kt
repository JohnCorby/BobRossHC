package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.Main.Companion.PLUGIN
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object Data {
    private const val NAME = "deadPlayers.dat"
    private lateinit var file: File

    var deadPlayers = mutableListOf<String>()

    fun load() {
//        PLUGIN.dataFolder.mkdir()
        file = File(PLUGIN.dataFolder, NAME)

        ObjectInputStream(file.inputStream()).use {
            deadPlayers = it.readObject() as MutableList<String>
        }
    }

    fun save() {
        ObjectOutputStream(file.outputStream()).use {
            it.writeObject(deadPlayers)
        }
    }
}
