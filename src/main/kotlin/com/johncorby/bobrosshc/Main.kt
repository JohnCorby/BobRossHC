/**
 * todo last-fired events instead of bypass permissions
 */
package com.johncorby.bobrosshc

import me.lucko.luckperms.LuckPerms
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

lateinit var PLUGIN: Main

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        PLUGIN = this
        PERM_API = LuckPerms.getApi()
        loadData()
        startTimeChecker()
        server.pluginManager.registerEvents(Listener, this)
    }

    override fun onDisable() = saveData()
}
