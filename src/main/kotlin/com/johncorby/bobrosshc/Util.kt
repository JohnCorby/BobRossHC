package com.johncorby.bobrosshc

import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.entity.Player

const val WORLD_PREFIX = "HardCore_Season_"
fun valid(player: Player) = player.world.name.startsWith(WORLD_PREFIX)

//const val PERM_PREFIX =
/**
 * workaround for multiverse
 * bypass forced gamemode so we can manage it ourselves
 *
 * permission: mv.bypass.gamemode.[*|WORLDNAME]
 */
fun bypassGamemode(world: World) {
}

fun markDead(player: Player) {
    player.gameMode = GameMode.SPECTATOR

    Data.deadPlayers.add(player.name)
}

fun reset() {

}
