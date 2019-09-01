package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.Main.Companion.LUCK_PERMS_API
import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.entity.Player

const val WORLD_PREFIX = "HardCore_Season_"
fun valid(world: World) = world.name.startsWith(WORLD_PREFIX)

//const val PERM_PREFIX =
/**
 * workaround for multiverse
 * bypass forced gamemode so we can manage it ourselves
 *
 * permission: mv.bypass.gamemode.[*|WORLDNAME]
 */
fun bypassGamemode(world: World) {
    LUCK_PERMS_API.
}

fun markDead(player: Player) {
    player.gameMode = GameMode.SPECTATOR

    Data.deadPlayers.add(player.name)
}

fun setGamemode(event

fun reset() {
    // make new world

    // reset dead players
}
