package com.johncorby.bobrosshc

import com.johncorby.coreapi.DelegateConfigFile

object Data : DelegateConfigFile("data.yml") {
    private const val NULL_TIME = "null"

    var currentSeason by Key("current-season", 0)
    var lastReset by Key("last-reset", NULL_TIME)
    var deadPlayers by Key("dead-players", mutableListOf<String>())

    init {
        // if last-reset isn't set, then we haven't had any seasons and should make a new one
        if (lastReset == NULL_TIME) newSeason()
    }
}
