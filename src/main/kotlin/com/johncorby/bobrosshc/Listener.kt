package com.johncorby.bobrosshc

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerTeleportEvent

object Listener : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        with(event.entity) {
            if (!valid()) return

            Data.deadPlayers.add(name)
            gameMode = GameMode.SPECTATOR
        }
    }

    @EventHandler
    fun onTeleport(event: PlayerTeleportEvent) {
        if (event.from.world == event.to.world) return
        if (!event.to.world.valid()) return

        with(event.player) {
            gameMode = if (name in Data.deadPlayers) GameMode.SPECTATOR else GameMode.SURVIVAL
        }
    }
}
