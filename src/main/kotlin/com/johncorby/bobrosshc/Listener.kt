/**
 * handles bukkit events
 */
package com.johncorby.bobrosshc

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerChangedWorldEvent

private val Player.valid get() = world.name.startsWith(WORLD_PREFIX)


const val WORLD_PREFIX = "HardCore_Season_"

object Listener : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) = with(event.entity) {
        if (!valid) return

        deadPlayers.add(name)
        gameMode = GameMode.SPECTATOR
    }

    @EventHandler
    fun onTeleport(event: PlayerChangedWorldEvent) = with(event.player) {
        if (!valid) return

        gameMode = if (name in deadPlayers) GameMode.SPECTATOR else GameMode.SURVIVAL
    }
}
