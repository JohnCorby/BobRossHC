package com.johncorby.bobrosshc

import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.node.Node

/**
 * handles permission and world tracking
 *
 * todo force update of gamemode instead of using bypass perms
 */
object PermHandler {
    var API = LuckPermsProvider.get()
    var GROUP = API.groupManager.getGroup("Trusted")!!

    inline val worldName get() = "$WORLD_PREFIX${Data.currentSeason}"
    inline val worldBypassPerm: Node get() = Node.builder("mv.bypass.gamemode.$worldName").build()
}
