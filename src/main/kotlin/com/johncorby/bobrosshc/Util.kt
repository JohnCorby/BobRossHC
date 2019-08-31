package com.johncorby.bobrosshc

import org.bukkit.entity.Player

const val WORLD_PREFIX = "HardCore_Season_"
fun valid(player: Player) = player.world.name.startsWith(WORLD_PREFIX)
