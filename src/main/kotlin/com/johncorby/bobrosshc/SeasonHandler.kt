package com.johncorby.bobrosshc

import com.johncorby.bobrosshc.Command.tp
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
    private inline val worldName get() = "${Config.WORLD_PREFIX}${Data.currentSeason}"

    init {
        // load existing season worlds
        server.worldContainer
            .list { _, name -> name.startsWith(Config.WORLD_PREFIX) }
            ?.forEach { WorldCreator(it).createWorld() }

        // init with new season if we havent reset before
        if (Data.lastReset == null) makeNew()

        // season check timer
        schedule(period = Config.SEASON_CHECK_INTERVAL * 20L) {
            if (daysUntilNext <= 0) makeNew()
        }
    }

    /**
     * gets the number of days until the next season will begin
     */
    val daysUntilNext
        get() = Config.SEASON_DURATION - Duration.between(
            Instant.parse(Data.lastReset!!),
            Instant.now()
        ).toDays()

    /**
     * gets the current world for this season
     */
    val currentWorld get() = server.getWorld(worldName)

    /**
     * start a new season
     */
    fun makeNew() = Data.use(true) {
        val oldWorld = currentWorld
        Data.deadPlayers.clear()
        Data.currentSeason++
        WorldCreator(worldName).createWorld()!!

        // tp players from old world to new one
        oldWorld?.players?.forEach {
            it.tp()
            it.info("a new season has started!")
        }

        // get rid of worlds older than n seasons
        // and also worlds that are higher than the current season
        val seasonWorlds: List<World> = server.worlds.filter { it.name.startsWith(Config.WORLD_PREFIX) }
        for (seasonWorld in seasonWorlds) {
            val season = seasonWorld.name.drop(Config.WORLD_PREFIX.length).toInt()
            if (season <= Data.currentSeason - Config.NUM_SEASONS_TO_KEEP || season > Data.currentSeason) {
                seasonWorld.players.forEach { it.tp() }

                server.unloadWorld(seasonWorld, false)
                seasonWorld.worldFolder.deleteRecursively()
            }
        }

        // update last reset time
        Data.lastReset = Instant.now().toString()
    }
}
