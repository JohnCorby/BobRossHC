/**
 * handles loading/saving of storage
 */
package com.johncorby.bobrosshc

import java.time.LocalDateTime
import kotlin.properties.Delegates

var currentSeason by Delegates.notNull<Int>()
lateinit var lastReset: LocalDateTime
lateinit var deadPlayers: MutableList<String>

fun loadData() {
    PLUGIN.saveDefaultConfig()

    currentSeason = PLUGIN.config.getInt("current-season")
    deadPlayers = PLUGIN.config.getStringList("dead-players")

    // if last-reset isn't set, then we haven't had any seasons and should make a new one
    if (PLUGIN.config.isSet("last-reset"))
        lastReset = LocalDateTime.parse(PLUGIN.config.getString("last-reset"))
    else
        newSeason()
}

fun saveData() {
    PLUGIN.config.set("current-season", currentSeason)
    PLUGIN.config.set("dead-players", deadPlayers)
    PLUGIN.config.set("last-reset", lastReset.toString())

    PLUGIN.saveConfig()
}
