package com.johncorby.bobrosshc

import com.johncorby.coreapi.info
import com.johncorby.coreapi.listen
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent

const val WORLD_PREFIX = "HardCore_Season_"

object Listener : Listener {
    private inline val Player.valid get() = world.name.startsWith(WORLD_PREFIX)
    private inline val Player.uuid get() = uniqueId.toString()

    init {
        listen<PlayerDeathEvent> {
            entity.apply {
                if (!valid) return@listen

                Data.deadPlayers.add(uuid)
                updateGameMode()

                info("rip you got fucked. better luck next season.")
            }
        }

        listen<PlayerJoinEvent> {
            player.apply {
                if (!valid) return@listen

                updateGameMode()
            }
        }

        listen<PlayerChangedWorldEvent> {
            player.apply {
                if (!valid) return@listen

                updateGameMode()
            }
        }
    }

    private fun Player.updateGameMode() {
        gameMode = if (uuid in Data.deadPlayers) GameMode.SPECTATOR else GameMode.SURVIVAL
    }
}
