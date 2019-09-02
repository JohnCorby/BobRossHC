package com.johncorby.bobrosshc

import org.bukkit.WorldCreator

private fun newSeason() {
    GROUP.unsetPermission(worldBypassPerm)
    currentSeason++
    GROUP.setPermission(worldBypassPerm)
    PERM_API.groupManager.saveGroup(GROUP)

    deadPlayers.clear()

    WorldCreator(worldName).createWorld()
}
