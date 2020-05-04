package com.johncorby.bobrosshc

import com.johncorby.coreapi.info
import com.johncorby.coreapi.listen
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerGameModeChangeEvent

/**
 * tracks death and handles changing gamemode
 */
object Listener : Listener {
    private inline val Player.valid get() = world.name.startsWith(Config.worldPrefix)
    private inline val Player.uuid get() = uniqueId.toString()

    init {
        listen<PlayerDeathEvent> {
            entity.apply {
                if (!valid) return@listen

                Data.deadPlayers.add(uuid)
                gameMode = GameMode.SPECTATOR

                info("rip you got fucked. better luck next season.")
            }
        }

        listen<PlayerGameModeChangeEvent> {
            player.apply {
                if (!valid) return@listen

                val desiredGameMode = if (uuid in Data.deadPlayers) GameMode.SPECTATOR else GameMode.SURVIVAL
                val wantedGameMode = newGameMode
                if (desiredGameMode != wantedGameMode) isCancelled = true
            }
        }
    }
}
