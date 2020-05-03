package com.johncorby.bobrosshc

import com.johncorby.coreapi.schedule
import org.bukkit.WorldCreator
import java.time.Duration
import java.time.Instant


/**
 * tracks the time between seasons.
 * every minute, we check if it's been 14 days since the last reset.
 * if it has, create a new season.
 */
object SeasonTracker {
    private const val INTERVAL = 60

    init {
        schedule(period = INTERVAL * 20L) {
            val time = Duration.between(
                Instant.parse(Data.lastReset),
                Instant.now()
            )
            if (time.toDays() >= 14) newSeason()
        }
    }

}

/**
 * start a new season
 */
fun newSeason() {
    // reset world
    WorldCreator(worldName).createWorld()
    Data.deadPlayers.clear()

    // update bypass permissions
    PERM_GROUP.data().remove(worldBypassPerm)
    Data.currentSeason++
    PERM_GROUP.data().add(worldBypassPerm)
    PERM_API.groupManager.saveGroup(PERM_GROUP)

    // the last reset was right now
    Data.lastReset = Instant.now().toString()
}
