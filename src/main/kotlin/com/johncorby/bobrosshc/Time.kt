package com.johncorby.bobrosshc

import org.bukkit.WorldCreator
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration
import java.time.LocalDateTime

/**
 * start time checker
 * every minute, we check if the time since last reset is longer than [14] days
 */
fun startTimeChecker() = object : BukkitRunnable() {
    override fun run() {
        if (Duration.between(lastReset, LocalDateTime.now()).toDays() >= 14)
            newSeason()
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
