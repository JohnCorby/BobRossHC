/**
 * hardcore: die = spectator
 */
package com.johncorby.bobrosshc

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.plugin.java.JavaPlugin

const val WORLD_PREFIX = "HardCore_Season_"

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        saveDefaultConfig()

        server.pluginManager.registerEvents(Listener, this)
    }

    /**
     * put player on spectator when they die in hardcore world
     */
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        if (event.entity.world.name.startsWith(WORLD_PREFIX))
            event.entity.gameMode = GameMode.SPECTATOR
    }

    /**
     * reset hc to new season (by creating a new world)
     */
    fun reset() {

    }
}
