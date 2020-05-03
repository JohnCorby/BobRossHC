package com.johncorby.bobrosshc

import com.johncorby.coreapi.Plugin
import com.johncorby.coreapi.info
import net.luckperms.api.LuckPermsProvider

class Main : Plugin() {
    override fun onEnable() {
        super.onEnable()

        PERM_API = LuckPermsProvider.get()
        PERM_GROUP = PERM_API.groupManager.getGroup("Trusted")!! // todo force update of gamemode instead of using bypass perms

        Data
        SeasonTracker
        Listener

        info("enabled")
    }

    override fun onDisable() {
        super.onDisable()

        info("disabled")
    }
}
