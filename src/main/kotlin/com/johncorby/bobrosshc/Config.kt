package com.johncorby.bobrosshc

import com.johncorby.coreapi.DelegateConfigFile

object Config : DelegateConfigFile("config.yml") {
    val WORLD_PREFIX by Key("world-prefix", "HardCore_Season_")
    val SEASON_DURATION by Key("season-duration", 14)
    val NUM_SEASONS_TO_KEEP by Key("num-seasons-to-keep", 5)
    val SEASON_CHECK_INTERVAL by Key("new-season-check-interval", 60)
}

object Data : DelegateConfigFile("data.yml") {
    const val NOT_STARTED = "not started"

    var currentSeason by Key("current-season", 0)
    var lastReset by Key("last-reset", NOT_STARTED)
    val deadPlayers by Key("dead-players", mutableListOf<String>())
}
