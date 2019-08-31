package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.Main.Companion.PLUGIN
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object Config {
    private const val NAME = "playerData.dat"
    private lateinit var file: File

    private var data = mutableMapOf<String, Boolean>()

    fun load() {
//        PLUGIN.dataFolder.mkdir()
        file = File(PLUGIN.dataFolder, NAME)

        ObjectInputStream(file.inputStream()).use {
            data = it.readObject() as MutableMap<String, Boolean>
        }
    }

    fun save() {
        ObjectOutputStream(file.outputStream()).use {
            it.writeObject(data)
        }
    }
}
