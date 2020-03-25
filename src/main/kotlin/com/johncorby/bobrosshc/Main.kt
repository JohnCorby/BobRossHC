package com.johncorby.bobrosshc

import net.luckperms.api.LuckPermsProvider
import org.bukkit.plugin.java.JavaPlugin

lateinit var PLUGIN: Main

class Main : JavaPlugin() {
    override fun onEnable() {
        PLUGIN = this

        PERM_API = LuckPermsProvider.get()
        PERM_GROUP = PERM_API.groupManager.getGroup("Trusted")!!

        loadData()
        startTimeChecker()
        server.pluginManager.registerEvents(Listener, this)
    }

    override fun onDisable() = saveData()
}
