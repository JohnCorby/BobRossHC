package com.johncorby.bobrosshc

import co.aikar.commands.BaseCommand
import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Subcommand
import com.johncorby.coreapi.PLUGIN
import com.johncorby.coreapi.definePermission
import com.johncorby.coreapi.info
import com.johncorby.coreapi.unitize
import org.bukkit.command.CommandSender
import org.bukkit.permissions.PermissionDefault

private const val PERM_DEFAULT = "bobrosshc.defaults"
private const val PERM_ADMIN = "bobrosshc.admin"

@CommandAlias("bobrosshc|bhc")
@CommandPermission(PERM_DEFAULT)
object Command : BaseCommand() {
    init {
        PaperCommandManager(PLUGIN).apply {
            definePermission(PERM_DEFAULT, defaultValue = PermissionDefault.TRUE)
            definePermission(PERM_ADMIN, defaultValue = PermissionDefault.OP)

            registerCommand(this@Command)
        }
    }

    @Subcommand("nextseason")
    fun nextSeason(sender: CommandSender) {
        sender.info("next season starts in ${unitize(SeasonHandler.daysUntilNext, "day")}")
    }

    @Subcommand("newseason")
    @CommandPermission(PERM_ADMIN)
    fun newSeason(sender: CommandSender) {
        SeasonHandler.makeNew()
        sender.info("started new season (season ${Data.currentSeason})")
    }
}
