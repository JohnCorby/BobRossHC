/**
 * hardcore: die = spectator
 */
package com.johncorby.bobrosshc

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    companion object {
        lateinit var PLUGIN: Main
    }

    override fun onEnable() {
        PLUGIN = this
        Data.load()
        server.pluginManager.registerEvents(Listener, this)
    }

    override fun onDisable() = Data.save()
}
