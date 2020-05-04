package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.PermHandler.API
import com.johncorby.bobrosshc.PermHandler.GROUP
import com.johncorby.bobrosshc.PermHandler.worldBypassPerm
import com.johncorby.bobrosshc.PermHandler.worldName
import com.johncorby.coreapi.schedule
import org.bukkit.WorldCreator
import java.time.Duration
import java.time.Instant


/**
 * handles tracking and creating new seasons.
 *
 * every minute, we check if it's been 14 days since the last reset.
 * if it has, create a new season.
 */
object SeasonHandler {
    private const val INTERVAL = 60

    init {
        schedule(period = INTERVAL * 20L) {
            val time = Duration.between(
                Instant.parse(Data.lastReset),
                Instant.now()
            )
            if (time.toDays() >= 14) makeNew()
        }
    }

    /**
     * start a new season
     */
    fun makeNew() {
        // reset world
        WorldCreator(worldName).createWorld()
        Data.deadPlayers.clear()

        // update bypass permissions
        GROUP.data().remove(worldBypassPerm)
        Data.currentSeason++
        GROUP.data().add(worldBypassPerm)
        API.groupManager.saveGroup(GROUP)

        // the last reset was right now
        Data.lastReset = Instant.now().toString()
    }
}
