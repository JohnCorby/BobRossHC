package com.johncorby.bobrosshc

import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerTeleportEvent

const val WORLD_PREFIX = "HardCore_Season_"

private fun World.valid() = name.startsWith(WORLD_PREFIX)
private fun Player.valid() = world.valid()


object Listener : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        with(event.entity) {
            if (!valid()) return

            deadPlayers.add(name)
            gameMode = GameMode.SPECTATOR
        }
    }

    @EventHandler
    fun onTeleport(event: PlayerTeleportEvent) {
        if (event.from.world == event.to.world) return
        if (!event.to.world.valid()) return

        with(event.player) {
            gameMode = if (name in deadPlayers) GameMode.SPECTATOR else GameMode.SURVIVAL
        }
    }
}
