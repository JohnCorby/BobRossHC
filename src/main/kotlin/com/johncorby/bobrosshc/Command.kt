package com.johncorby.bobrosshc

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.PaperCommandManager
import co.aikar.commands.annotation.*
import com.johncorby.coreapi.PLUGIN
import com.johncorby.coreapi.definePermissions
import com.johncorby.coreapi.info
import com.johncorby.coreapi.unitize
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause.COMMAND
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

private const val PERM_ADMIN = "bobrosshc.admin"
private const val PERM_DEFAULT = "bobrosshc.default"

@CommandAlias("bobrosshc|bhc")
@CommandPermission(PERM_DEFAULT)
object Command : BaseCommand() {
    init {
        PaperCommandManager(PLUGIN).apply {
            enableUnstableAPI("help")

            definePermissions(
                Permission(PERM_ADMIN, PermissionDefault.OP),
                Permission(PERM_DEFAULT, PermissionDefault.TRUE)
            )

            registerCommand(this@Command)
        }
    }

    @HelpCommand
    fun help(help: CommandHelp) = help.showHelp()


    @Default
    @Description("teleport to the season world")
    fun Player.tp() {
        info("teleporting to season world")
        teleport(SeasonHandler.currentWorld!!.spawnLocation, COMMAND)
    }

    @Subcommand("season")
    @Description("check when the next season starts")
    fun CommandSender.seasonInfo() {
        info("next season starts in ${unitize(SeasonHandler.daysUntilNext, "day")}")
    }

    @Subcommand("season next")
    @Description("force the next season to start")
    @CommandPermission(PERM_ADMIN)
    fun CommandSender.nextSeason() {
        SeasonHandler.makeNew()
        info("started new season (season ${Data.currentSeason})")
    }

    @Subcommand("season reset")
    @Description("reset the season counter")
    @CommandPermission(PERM_ADMIN)
    fun CommandSender.resetSeason() {
        Data.currentSeason = 0
        SeasonHandler.makeNew()
        info("season counter reset")
    }
}
