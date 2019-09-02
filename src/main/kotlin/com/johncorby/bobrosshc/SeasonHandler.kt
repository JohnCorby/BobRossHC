/**
 * handles creation of new seasons and checking elapsed time
 */
package com.johncorby.bobrosshc

import org.bukkit.WorldCreator
import org.bukkit.scheduler.BukkitRunnable
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * start time checker
 * every minute, we check if the time since last reset is longer than 14 days
 */
fun startTimeChecker() = object : BukkitRunnable() {
    override fun run() {
        if (ChronoUnit.DAYS.between(lastReset, LocalDateTime.now()) >= 14)
            newSeason()
    }
}.runTaskTimer(PLUGIN, 0, 60 * 20)

fun newSeason() {
    // reset world
    WorldCreator(worldName).createWorld()
    deadPlayers.clear()

    // update permissions
    PERM_GROUP.unsetPermission(worldBypassPerm)
    currentSeason++
    PERM_GROUP.setPermission(worldBypassPerm)
    PERM_API.groupManager.saveGroup(PERM_GROUP)

    // the last reset was right now
    lastReset = LocalDateTime.now()
}
