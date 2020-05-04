package com.johncorby.bobrosshc

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.*
import com.johncorby.coreapi.PLUGIN
import com.johncorby.coreapi.definePermission
import com.johncorby.coreapi.info
import com.johncorby.coreapi.unitize
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault

private const val PERM_DEFAULT = "bobrosshc.defaults"
private const val PERM_ADMIN = "bobrosshc.admin"

@CommandAlias("bobrosshc|bhc")
@CommandPermission(PERM_DEFAULT)
object Command : BaseCommand() {
    init {
        PaperCommandManager(PLUGIN).apply {
            enableUnstableAPI("help")

            definePermission(PERM_DEFAULT, defaultValue = PermissionDefault.TRUE)
            definePermission(PERM_ADMIN, defaultValue = PermissionDefault.OP)

            registerCommand(this@Command)
        }
    }

    @HelpCommand
    fun help(sender: CommandSender, help: CommandHelp) = help.showHelp()


    @Default
    @Description("teleport to the season world")
    fun teleport(sender: Player) {
        sender.info("teleporting to season world")
        sender.teleport(SeasonHandler.currentWorld!!.spawnLocation)
    }

    @Subcommand("season")
    @Description("check when the next season starts")
    fun seasonInfo(sender: CommandSender) {
        sender.info("next season starts in ${unitize(SeasonHandler.daysUntilNext, "day")}")
    }

    @Subcommand("season next")
    @Description("force the next season to start")
    @CommandPermission(PERM_ADMIN)
    fun nextSeason(sender: CommandSender) {
        SeasonHandler.makeNew()
        sender.info("started new season (season ${Data.currentSeason})")
    }

    @Subcommand("season reset")
    @Description("reset the season counter")
    @CommandPermission(PERM_ADMIN)
    fun resetSeason(sender: CommandSender) {
        Data.currentSeason = 0
        SeasonHandler.makeNew()
        sender.info("season counter reset")
        if (sender is Player) teleport(sender)
    }
}
