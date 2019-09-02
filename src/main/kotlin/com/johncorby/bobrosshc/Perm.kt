package com.johncorby.bobrosshc

import me.lucko.luckperms.api.LuckPermsApi

private const val GROUP_NAME = "Trusted"
private const val PREFIX = "mv.bypass.gamemode."


lateinit var PERM_API: LuckPermsApi
val GROUP = PERM_API.getGroup(GROUP_NAME)!!

val worldName get() = "$WORLD_PREFIX$currentSeason"
val worldBypassPerm get() = PERM_API.buildNode("$PREFIX.$worldName").build()
