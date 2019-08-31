package com.johncorby.bobrosshc

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

object Listener : Listener {
    /**
     * put player on spectator when they die in hardcore world
     */
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        if (valid(event.entity)) event.entity.gameMode = GameMode.SPECTATOR
    }
}
