package com.johncorby.bobrosshc

import kotlin.properties.Delegates


var currentSeason by Delegates.notNull<Int>()
lateinit var lastReset: String
lateinit var deadPlayers: MutableList<String>

fun loadData() {
    PLUGIN.saveDefaultConfig()
    currentSeason = PLUGIN.config.getInt("current-season")
    lastReset = PLUGIN.config.getString("last-reset")!!
    deadPlayers = PLUGIN.config.getStringList("dead-players")
}

fun saveData() {
    PLUGIN.config.set("current-season", currentSeason)
    PLUGIN.config.set("last-reset", currentSeason)
    PLUGIN.config.set("dead-players", deadPlayers)
    PLUGIN.saveConfig()
}
