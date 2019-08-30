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
        server.pluginManager.registerEvents(this, this)
        logger.info("$name enabled")
    }

    override fun onDisable() = logger.info("$name disabled")

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        if (event.entity.world.name.startsWith(WORLD_PREFIX))
            event.entity.gameMode = GameMode.SPECTATOR
    }
}
