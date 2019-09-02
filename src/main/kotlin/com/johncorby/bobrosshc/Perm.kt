package com.johncorby.bobrosshc

import me.lucko.luckperms.api.LuckPermsApi

lateinit var PERM_API: LuckPermsApi
val PERM_GROUP = PERM_API.getGroup("Trusted")!!

val worldName get() = "$WORLD_PREFIX$currentSeason"
val worldBypassPerm get() = PERM_API.buildNode("mv.bypass.gamemode.$worldName").build()
