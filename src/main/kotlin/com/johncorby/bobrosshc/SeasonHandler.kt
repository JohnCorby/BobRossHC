package com.johncorby.bobrosshc

import com.johncorby.coreapi.info
import com.johncorby.coreapi.schedule
import hazae41.minecraft.kutils.bukkit.server
import org.bukkit.World
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
    private inline val worldName get() = "${Config.worldPrefix}${Data.currentSeason}"

    init {
        // load existing season worlds
        val seasonWorlds = server.worldContainer.list()!!.filter { it.startsWith(Config.worldPrefix) }
        seasonWorlds.forEach {
            WorldCreator(it).createWorld()
        }

        if (Data.lastReset == Data.NOT_STARTED) makeNew()

        schedule(period = Config.seasonCheckInterval * 20L) {
            if (daysUntilNext <= 0) makeNew()
        }
    }

    /**
     * gets the number of days until the next season will begin
     */
    val daysUntilNext
        get() = Config.seasonDuration - Duration.between(
            Instant.parse(Data.lastReset),
            Instant.now()
        ).toDays()

    /**
     * gets the current world for this season
     */
    val currentWorld get() = server.getWorld(worldName)

    /**
     * start a new season
     */
    fun makeNew() {
        val oldWorld = currentWorld
        Data.deadPlayers.clear()
        Data.currentSeason++
        val newWorld = WorldCreator(worldName).createWorld()!!

        // tp players from old world to new one
        oldWorld?.players?.forEach {
            it.teleport(newWorld.spawnLocation)
            it.info("a new season has started!")
        }

        // get rid of worlds older than n seasons
        // and also worlds that are higher than the current season
        val seasonWorlds: List<World> = server.worlds.filter { it.name.startsWith(Config.worldPrefix) }
        seasonWorlds.forEach {
            val season = it.name.drop(Config.worldPrefix.length).toInt()
            if (season <= Data.currentSeason - Config.numSeasonsToKeep || season > Data.currentSeason) {
                server.unloadWorld(it, false)
                it.worldFolder.deleteRecursively()
            }
        }

        // update last reset time
        Data.lastReset = Instant.now().toString()
    }
}
