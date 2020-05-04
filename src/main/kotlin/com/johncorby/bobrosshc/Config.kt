package com.johncorby.bobrosshc

import com.johncorby.coreapi.DelegateConfigFile

object Config : DelegateConfigFile("config.yml") {
    val worldPrefix by Key("world-prefix", "HardCore_Season_")
    val seasonDuration by Key("season-duration", 14)
    val numSeasonsToKeep by Key("num-seasons-to-keep", 5)
    val seasonCheckInterval by Key("new-season-check-interval", 60)
}

object Data : DelegateConfigFile("data.yml") {
    const val NOT_STARTED = "not started"

    var currentSeason by Key("current-season", 0)
    var lastReset by Key("last-reset", NOT_STARTED)
    val deadPlayers by Key("dead-players", mutableListOf<String>())
}
