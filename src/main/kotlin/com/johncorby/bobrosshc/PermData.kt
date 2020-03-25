/**
 * stores proper bypass permissions
 */
package com.johncorby.bobrosshc

import net.luckperms.api.LuckPerms
import net.luckperms.api.model.group.Group
import net.luckperms.api.node.Node

lateinit var PERM_API: LuckPerms
lateinit var PERM_GROUP: Group

inline val worldName get() = "$WORLD_PREFIX$currentSeason"
inline val worldBypassPerm: Node get() = Node.builder("mv.bypass.gamemode.$worldName").build()
