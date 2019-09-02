/**
 * hardcore: die = spectator
 */
package com.johncorby.bobrosshc

import me.lucko.luckperms.LuckPerms
import me.lucko.luckperms.api.LuckPermsApi
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    companion object {
        lateinit var PLUGIN: Main
        lateinit var PERM_API: LuckPermsApi
    }

    override fun onEnable() {
        PLUGIN = this
        PERM_API = LuckPerms.getApi()
        Data.load()
        server.pluginManager.registerEvents(Listener, this)
    }

    override fun onDisable() = Data.save()
}
