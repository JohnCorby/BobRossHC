package com.johncorby.bobrosshc

import com.johncorby.coreapi.info
import com.johncorby.coreapi.listen
import com.johncorby.coreapi.schedule
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent

/**
 * tracks death and handles changing gamemode
 */
object Listener : Listener {
    private inline val Player.valid get() = world.name.startsWith(Config.WORLD_PREFIX)
    private inline val Player.uuid get() = uniqueId.toString()

    private inline val Player.expectedGameMode get() = if (uuid in Data.deadPlayers) GameMode.SPECTATOR else GameMode.SURVIVAL

    init {
        listen<PlayerDeathEvent> {
            if (!entity.valid) return@listen

            Data.use(true) { Data.deadPlayers.add(entity.uuid) }
            entity.gameMode = GameMode.SPECTATOR

            entity.info("rip you got fucked. better luck next season.")
            isCancelled = true
        }

        listen<PlayerChangedWorldEvent> {
            if (!player.valid) return@listen
            // schedule to bypass inventory manager
            schedule {
                player.gameMode = player.expectedGameMode
                player.inventory.clear() // fixme clears inventory every time any player enters the world
            }
        }
        listen<PlayerJoinEvent> {
            if (!player.valid) return@listen
            // schedule to bypass inventory manager
            schedule {
                player.gameMode = player.expectedGameMode
            }
        }
    }
}
