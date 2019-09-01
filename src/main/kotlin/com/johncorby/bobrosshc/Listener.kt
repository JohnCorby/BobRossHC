package com.johncorby.bobrosshc

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerTeleportEvent

object Listener : Listener {
    /**
     * put player on spectator when they die in hardcore world
     */
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        if (valid(event.entity.world)) markDead(event.entity)
    }

    @EventHandler
    fun onTeleport(event: PlayerTeleportEvent) {
        if (event.from == event.to) return
        if (!valid(event.to.world)) return
    }
}
