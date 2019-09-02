package com.johncorby.bobrosshc

import org.bukkit.WorldCreator
import org.bukkit.scheduler.BukkitRunnable
import java.time.LocalDateTime

fun startTimeChecker() = object : BukkitRunnable() {
    override fun run() {

    }
}.runTaskTimer(PLUGIN, 0, 60 * 20)

fun newSeason() {
    WorldCreator(worldName).createWorld()
    deadPlayers.clear()

    PERM_GROUP.unsetPermission(worldBypassPerm)
    currentSeason++
    PERM_GROUP.setPermission(worldBypassPerm)
    PERM_API.groupManager.saveGroup(PERM_GROUP)

    lastReset = LocalDateTime.now()
}
