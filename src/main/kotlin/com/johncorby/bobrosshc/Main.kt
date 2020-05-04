package com.johncorby.bobrosshc

import com.johncorby.coreapi.Plugin
import com.johncorby.coreapi.info

class Main : Plugin() {
    override fun onEnable() {
        super.onEnable()

        PermHandler
        Data
        SeasonHandler
        Listener

        info("enabled")
    }

    override fun onDisable() {
        super.onDisable()

        info("disabled")
    }
}
