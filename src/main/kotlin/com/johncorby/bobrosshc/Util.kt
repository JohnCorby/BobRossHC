package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.Main.Companion.PERM_API
import com.johncorby.bobrosshc.Main.Companion.PERM_GROUP
import me.lucko.luckperms.api.Node
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.entity.Player

const val DATA_FILE_NAME = "deadPlayers.dat"
const val WORLD_PREFIX = "HardCore_Season_"
const val PERM_GROUP_NAME = "Trusted"
const val PERM_PREFIX = "mv.bypass.gamemode."

fun World.valid() = name.startsWith(WORLD_PREFIX)
fun Player.valid() = world.valid()

/**
 * workaround for multiverse
 * bypass forced gamemode so we can manage it ourselves
 *
 * permission: mv.bypass.gamemode.[*|WORLDNAME]
 */
fun bypassGamemode(world: World) {
    // turn
    PERM_API.knownPermissions
}

fun newSeason() {
    val group = PERM_API.getGroup(PERM_GROUP_NAME)!!
    group.unsetPermission(PERM_API.buildNode(PERM_PREFIX + Data.currentSeason).build())

    Data.currentSeason++
    Data.deadPlayers.clear()

    PERM_API.groupManager.saveGroup(group)

    WorldCreator()


}
