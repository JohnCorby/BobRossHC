package com.johncorby.bobrosshc

import com.johncorby.coreapi.ConfigFile

object Config : ConfigFile() {
    val WORLD_PREFIX by field("world-prefix", "HardCore_Season_")
    val SEASON_DURATION by field("season-duration", 14)
    val NUM_SEASONS_TO_KEEP by field("num-seasons-to-keep", 5)
    val SEASON_CHECK_INTERVAL by field("new-season-check-interval", 60)
}

object Data : ConfigFile("data.yml") {
    var currentSeason by field("current-season", 0)
    var lastReset by field<String?>("last-reset")
    var deadPlayers by field("dead-players", mutableListOf<String>())
}
