package com.johncorby.bobrosshc

import com.johncorby.coreapi.ConfigFile

object Config : ConfigFile() {
    val WORLD_PREFIX by Field("world-prefix", "HardCore_Season_")
    val SEASON_DURATION by Field("season-duration", 14)
    val NUM_SEASONS_TO_KEEP by Field("num-seasons-to-keep", 5)
    val SEASON_CHECK_INTERVAL by Field("new-season-check-interval", 60)
}

object Data : ConfigFile("data.yml") {
    var currentSeason by Field("current-season", 0)
    var lastReset by Field<String?>("last-reset")
    val deadPlayers by Field("dead-players", mutableListOf<String>())
}
